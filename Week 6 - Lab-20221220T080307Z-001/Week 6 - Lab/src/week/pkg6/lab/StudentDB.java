/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package week.pkg6.lab;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author emoha
 */

public class StudentDB {
    //consts
    private final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/cis355a";
    private final String USER_NAME = "root";
    private final String PASSWORD = "devry123";
    //behavs
    //save a student object to the database
    public void add(Student stu) throws ClassNotFoundException, SQLException{
        // check for the driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        // connect to the database
        Connection conn = DriverManager.getConnection(CONNECTION_STRING, USER_NAME, PASSWORD);
        
        // write records to db
        String sqlStr = "INSERT INTO students (StudentID, studentName, Test1, Test2, Test3)" + "VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sqlStr);
        pstmt.setInt(1, stu.getId()+1);
        pstmt.setString(2, stu.getName());
        pstmt.setDouble(3,stu.getTest1());
        pstmt.setDouble(4,stu.getTest2());
        pstmt.setDouble(5,stu.getTest3());
        pstmt.execute();
        
        // close the connection
        conn.close();
    }
    
    //
    public ArrayList<Student> getAll() throws ClassNotFoundException, SQLException {
        //create an empty ArrayList
        ArrayList<Student> list = new ArrayList<Student>();
        
        //Check for the driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        // connect to the database
        Connection conn = DriverManager.getConnection(CONNECTION_STRING, USER_NAME, PASSWORD);
        //get records form the database
        String strSQL = "SELECT * FROM students";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(strSQL);
        
        while(rs.next()){
            int stuID = rs.getInt(1);
            stuID++;
            String name = rs.getString(2);
            double test1 = rs.getDouble(3);
            double test2 = rs.getDouble(4);
            double test3 = rs.getDouble(5);
        
            Student stu =new Student(stuID, name, test1, test2, test3);
        
            list.add(stu);
        }
        //close the connection to the database
        conn.close();
        
        //return the arraylist
        return list;
    }
}

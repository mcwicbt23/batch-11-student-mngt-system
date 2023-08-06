package com.studentmngt.daos;

import com.studentmngt.entities.Student;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    private DataSource dataSource;

    public StudentDAO(){
        try {
            InitialContext ic = new InitialContext();
            Context xmlContext = (Context) ic.lookup("java:comp/env");
            dataSource = (DataSource) xmlContext.lookup("jdbc/student_db_connection");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public List<Student> getStudents(){
        //Business Logic
        List<Student> students = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet resultSet = null;

        try {
            con = dataSource.getConnection();
            stmt = con.createStatement();

            String query = "SELECT * FROM student";
            resultSet = stmt.executeQuery(query);

            while (resultSet.next()){
                Integer id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");

                Student student = new Student(id, firstName, lastName, email);
                students.add(student);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(con, stmt, resultSet);
        }

        return students;
    }

    public void addStudent(Student student) {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = dataSource.getConnection();
            String sql = "INSERT INTO student (first_name, last_name, email) VALUES (?, ?, ?)";
            stmt = con.prepareStatement(sql);

            stmt.setString(1, student.getFirstName());
            stmt.setString(2, student.getLastName());
            stmt.setString(3, student.getEmail());

            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(con, stmt, null);
        }
    }

    public Student readStudent(int studentId) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rset = null;
        Student student = null;
        try {
            con = dataSource.getConnection();
            String sql = "SELECT * FROM student WHERE id=?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, studentId);

            rset = stmt.executeQuery();
            if (rset.next()){
                Integer id = rset.getInt("id");
                String firstName = rset.getString("first_name");
                String lastName = rset.getString("last_name");
                String email = rset.getString("email");

                student = new Student(id, firstName, lastName, email);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(con, stmt, rset);
        }
        return student;
    }

    private void close(Connection con, Statement stmt, ResultSet rset){
        try {
            if(rset != null){
                rset.close();
            }
            if(stmt != null) {
                stmt.close();
            }
            if(con != null){
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStudent(Student student) {
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = dataSource.getConnection();
            String sql = "UPDATE student SET first_name=?, last_name=?, email=? WHERE id=?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, student.getFirstName());
            stmt.setString(2, student.getLastName());
            stmt.setString(3, student.getEmail());
            stmt.setInt(4, student.getId());

            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(con, stmt, null);
        }
    }

    public void deleteStudent(Integer studentId) {
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = dataSource.getConnection();
            String sql = "DELETE FROM student WHERE id=?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, studentId);

            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(con, stmt, null);
        }
    }
}

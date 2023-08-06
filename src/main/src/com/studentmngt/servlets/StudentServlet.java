package com.studentmngt.servlets;

import com.studentmngt.daos.StudentDAO;
import com.studentmngt.entities.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command"); //ADD-addStudent(), UPDATE-updateStudent()
        if(command == null){
            command = "LIST";
        }

        switch (command){
            case "ADD":
                addStudent(request, response);
                break;
            case "UPDATE":
                updateStudent(request, response);
                break;
            default:
                listStudents(request, response);
        }
    }

    private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Integer studentId = Integer.parseInt(request.getParameter("studentId"));
        String firstName = request.getParameter("first-name");
        String lastName = request.getParameter("last-name");
        String email = request.getParameter("email");

        Student student = new Student(studentId, firstName, lastName, email);
        StudentDAO studentDAO = new StudentDAO();
        studentDAO.updateStudent(student);

        listStudents(request, response);
    }

    private void addStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("first-name");
        String lastName = request.getParameter("last-name");
        String email = request.getParameter("email");

        Student student = new Student(firstName, lastName, email);
        StudentDAO  studentDAO = new StudentDAO();
        studentDAO.addStudent(student);

        listStudents(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command"); //LIST-listStudents(), READ-readStudent()
        if(command == null){
            command = "LIST";
        }

        switch (command){
            case "LIST":
                listStudents(request, response);
                break;
            case "READ":
                readStudent(request, response);
                break;
            case "DELETE":
                deleteStudent(request, response);
                break;
            default:
                listStudents(request, response);
                break;
        }

        //
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer studentId = Integer.parseInt(request.getParameter("studentId"));

        StudentDAO studentDAO = new StudentDAO();
        studentDAO.deleteStudent(studentId);

        listStudents(request, response);
    }

    private void readStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentIdStr = request.getParameter("studentId");
        int studentId = Integer.parseInt(studentIdStr);

        StudentDAO studentDAO = new StudentDAO();
        Student student = studentDAO.readStudent(studentId);

        request.setAttribute("student", student);

        RequestDispatcher dispatcher = request.getRequestDispatcher("update-student.jsp");
        dispatcher.forward(request, response);
    }

    private void listStudents(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        StudentDAO model = new StudentDAO();
        List<Student> students = model.getStudents();

        request.setAttribute("student_list", students);

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
}

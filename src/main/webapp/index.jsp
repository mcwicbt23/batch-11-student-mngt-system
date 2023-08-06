<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Student Management System</title>
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
    <jsp:include page="header.jsp"></jsp:include>

    <div class="container p-5">
        <div class="row">
            <div class="col-10">
                <h4>View Student</h4>
            </div>
            <div class="col-2">
                <button type="button" class="btn btn-primary float-end" onclick="window.location.href='add-student.jsp'">Add Student</button>
            </div>
        </div>
        <div class="table-responsive mt-3">
            <table class="table table-bordered table-striped">
                <tr>
                    <th>ID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Actions</th>
                </tr>
                <c:forEach var="student" items="${student_list}">

                    <c:url var="updateLink" value="StudentServlet">
                        <c:param name="command" value="READ"></c:param>
                        <c:param name="studentId" value="${student.id}"></c:param>
                    </c:url>

                    <c:url var="deleteLink" value="StudentServlet">
                        <c:param name="command" value="DELETE"></c:param>
                        <c:param name="studentId" value="${student.id}"></c:param>
                    </c:url>

                    <tr>
                        <td>${student.id}</td>
                        <td>${student.firstName}</td>
                        <td>${student.lastName}</td>
                        <td>${student.email}</td>
                        <td>
                            <a href="${updateLink}">Update</a>
                            | <a href="${deleteLink}" onclick="if(!confirm('Are you sure you want to delete this student?')) return false;">Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>

    <jsp:include page="footer.jsp"></jsp:include>
</body>
</html>

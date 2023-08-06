
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<html>
<head>
    <title>Update Student</title>
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>

<div class="container p-5">

    <div class="row ">
        <div class="col-12">
            <h4>Update Student</h4>
        </div>
    </div>

    <div class="row justify-content-center">
        <div class="col-6">
            <form action="StudentServlet" method="POST">
                <input type="hidden" name="studentId" value="${student.id}">
                <input type="hidden" name="command" value="UPDATE">
                <div class="mb-3">
                    <label for="firstName" class="form-label">First Name</label>
                    <input type="text" class="form-control" id="firstName" name="first-name" value="${student.firstName}">
                </div>
                <div class="mb-3">
                    <label for="lastName" class="form-label">Last Name</label>
                    <input type="text" class="form-control" id="lastName" name="last-name" value="${student.lastName}">
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="text" class="form-control" id="email" name="email" value="${student.email}">
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
            <p class="pt-3">
                <a href="StudentServlet">Back to List</a>
            </p>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>

<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>User List</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/4.3.1/css/bootstrap.min.css"/>
    <style><%@include file="../../resources/css/listUser.css"%></style>
</head>
<body>
<div class="container">
    <div class="text-right">
        <button class="btn btn-outline-danger mt-5" id="logout">
            <span class="spinner-border spinner-border-sm" role="status" id="logout_spinner" aria-hidden="true"></span>
            <span id="logout_text">Logout</span>
        </button>
    </div>
    <h1 class="display-3">User List</h1>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Name</th>
            <th scope="col">Surname</th>
            <th scope="col">Email</th>
            <th scope="col">Phone Number</th>
            <th scope="col">Date of Birth</th>
            <th scope="col">Create Date</th>
            <th scope="col">Role</th>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <th scope="col">Update</th>
                <th scope="col">Delete</th>
            </sec:authorize>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr class="table-color">
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.surname}</td>
                <td>${user.email}</td>
                <td>${user.phoneNumber}</td>
                <td><fmt:formatDate value="${user.birthDate}"/></td>
                <td>${user.createDate}</td>
                <td>${user.role}</td>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <td>
                    <c:if test="${user.role != 'ADMIN'}">
                        <button class="btn btn-warning" data-toggle="modal" data-target="#updateModal" value="${user.id}">Update</button>
                    </c:if>
                    </td>
                    <td>
                    <c:if test="${user.role != 'ADMIN'}">
                        <button class="btn btn-danger" data-toggle="modal" data-target="#deleteModal" value="${user.id}">Delete</button>
                    </c:if>
                    </td>
                </sec:authorize>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <%-- delete modal --%>
    <div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="deleteModalTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="deleteModalTitle">Delete User</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    Are you sure you want to delete this user?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                    <button type="button" class="btn btn-info">
                        <span class="spinner-border spinner-border-sm" role="status" id="delete_spinner" aria-hidden="true"></span>
                        <span id="delete_text">Yes, delete</span>
                    </button>
                </div>
            </div>
        </div>
    </div>

    <%-- update modal --%>
    <div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="updateModalTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="updateModalTitle">Update User</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form>
                        <h2 class="display-3">Update</h2>
                        <p>
                            <label for="name" class="sr-only">Name</label>
                            <input type="text" id="name" name="name" class="form-control" placeholder="Name" required="" autofocus="">
                        </p>
                        <p>
                            <label for="surname" class="sr-only">Surname</label>
                            <input type="text" id="surname" name="surname" class="form-control" placeholder="Surname" required="">
                        </p>
                        <p>
                            <label for="email" class="sr-only">Username</label>
                            <input type="email" id="email" name="email" class="form-control" placeholder="Email" required="">
                        </p>
                        <p>
                            <label for="phoneNumber" class="sr-only">National Identity</label>
                            <input type="text" id="phoneNumber" name="phoneNumber" class="form-control" placeholder="Phone Number" required="">
                        </p>
                        <p>
                            <label for="birthDate" class="sr-only">Date of Birth</label>
                            <input type="date" id="birthDate" name="birthDate" class="form-control" required="">
                        </p>
                        <div class="alert alert-danger" role="alert" id="errors"></div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                    <button type="button" class="btn btn-outline-warning">
                        <span class="spinner-border spinner-border-sm" role="status" id="update_spinner" aria-hidden="true"></span>
                        <span id="update_text">Update</span>
                    </button>
                </div>
            </div>
        </div>
    </div>

    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <button class="btn btn-success btn-block" data-toggle="modal" data-target="#addUserModal">Add User</button>

        <%-- add user modal --%>
        <div class="modal fade" id="addUserModal" tabindex="-1" role="dialog" aria-labelledby="addUserModalTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="addUserModalTitle">Add User</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form>
                        <h2 class="display-3">Add User</h2>
                        <p>
                            <label for="user_name" class="sr-only">Name</label>
                            <input type="text" id="user_name" name="name" class="form-control" placeholder="Name" required="" autofocus="">
                        </p>
                        <p>
                            <label for="user_surname" class="sr-only">Surname</label>
                            <input type="text" id="user_surname" name="surname" class="form-control" placeholder="Surname" required="">
                        </p>
                        <p>
                            <label for="user_email" class="sr-only">Username</label>
                            <input type="email" id="user_email" name="email" class="form-control" placeholder="Email" required="">
                        </p>
                        <p>
                            <label for="user_password" class="sr-only">Password
                            </label>
                            <input type="password" id="user_password" name="password" class="form-control" placeholder="Password" required="">
                        </p>
                        <p>
                            <label for="user_phoneNumber" class="sr-only">National Identity</label>
                            <input type="text" id="user_phoneNumber" name="phoneNumber" class="form-control" placeholder="Phone Number" required="">
                        </p>
                        <p>
                            <label for="user_birthDate" class="sr-only">Date of Birth</label>
                            <input type="date" id="user_birthDate" name="birthDate" class="form-control" required="">
                        </p>
                        <div class="alert alert-danger" role="alert" id="user_error"></div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                    <button type="button" class="btn btn-outline-success" id="add_user">
                        <span class="spinner-border spinner-border-sm" role="status" id="add_spinner" aria-hidden="true"></span>
                        <span id="add_text">Add</span>
                    </button>
                </div>
            </div>
        </div>
    </div>
    </sec:authorize>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/webjars/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script type="text/javascript"><%@include file="../../resources/js/listUser.js"%></script>
</body>
</html>
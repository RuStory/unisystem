<%@ page import="java.util.ArrayList" %>
<%@ page import="entities.Teacher" %>
<%@ page language="java" contentType="text/html; charset=Cp1251" pageEncoding="Cp1251"%>
<meta http-equiv="content-type" content="text/html; charset=cp1251">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<!-- saved from url=(0044)http://getbootstrap.com/examples/offcanvas/# -->
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="http://getbootstrap.com/favicon.ico">

    <title>Schedule</title>

    <!-- Bootstrap core CSS -->
    <link href="http://getbootstrap.com/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="http://getbootstrap.com/examples/offcanvas/offcanvas.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="./Off Canvas Template for Bootstrap_files/ie-emulation-modes-warning.js"></script><style type="text/css"></style>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<div class="modal fade" id="add-teacher" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="exampleModalLabel">New teacher</h4>
            </div>
            <div class="modal-body">
                <form id="new-teacher-form" action="/controller">
                    <div class="form-group">
                        <label class="control-label">Name</label>
                        <input type="text" name="name" required="true" class="form-control">
                    </div>
                    <div class="form-group">
                        <label class="control-label">Surname</label>
                        <input type="text" name="surname" required="true" class="form-control">
                    </div>
                    <input type="hidden" name="command" value="new_teacher">
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" onclick="document.getElementById('new-teacher-form').submit()">Save</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="add-room" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">New room</h4>
            </div>
            <div class="modal-body">
                <form id="new-room-form" action="/controller">
                    <div class="form-group">
                        <label class="control-label">Number</label>
                        <input type="text" name="number" required="true" class="form-control">
                    </div>
                    <input type="hidden" name="command" value="new_room">
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" onclick="document.getElementById('new-room-form').submit()">Save</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="add-student" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">New student</h4>
            </div>
            <div class="modal-body">
                <form id="new-student-form" action="/controller">
                    <div class="form-group">
                        <label class="control-label">Name</label>
                        <input type="text" name="name" required="true" class="form-control">
                    </div>
                    <div class="form-group">
                        <label class="control-label">Surname</label>
                        <input type="text" name="surname" required="true" class="form-control">
                    </div>
                    <input type="hidden" name="command" value="new_student">
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" onclick="document.getElementById('new-student-form').submit()">Save</button>
            </div>
        </div>
    </div>
</div>


<div class="container">
    <button type="button" class="btn btn-success" data-toggle="modal" data-target="#add-teacher">Add new teacher</button>
    <button type="button" class="btn btn-success" data-toggle="modal" data-target="#add-room">Add new room</button>
    <button type="button" class="btn btn-success" data-toggle="modal" data-target="#add-student">Add new student</button>
    <button type="button" class="btn btn-success" data-toggle="modal" data-target="#add-lesson" onclick="document.getElementById('lesson-form').submit()">Add new lesson</button>
    <form action="/controller" id="lesson-form">
        <input type="hidden" name="command" value="lesson_data">
    </form>
    <hr/>
    <div class="row row-offcanvas row-offcanvas-right">

        <%
            ArrayList<Teacher> teachers = (ArrayList<Teacher>)request.getAttribute("teachers");
            Integer teacherId = (Integer)request.getAttribute("currentTeacher");
            Teacher currentTeacher = null;
            for (Teacher newTeacher : teachers) {
                if( newTeacher.getId() == teacherId){
                    currentTeacher = newTeacher;
                    break;
                }
            }
        %>
        <div class="col-xs-12 col-sm-9">
            <p class="pull-right visible-xs">
                <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
            </p>
            <div class="jumbotron">
                <%--<p>Schedule of lessons for ${teachers[currentTeacher - 1].name} ${teachers[currentTeacher - 1].surname}</p>--%>
                <p>Schedule of lessons for <%=currentTeacher.getName()%> <%=currentTeacher.getSurname()%></p>
            </div>
            <div class="row">
                <c:forEach var="lesson" items="${lessons}">
                    <div class="col-xs-6 col-lg-4">
                        <h2>${lesson.name} ${lesson.time}</h2>

                        <p>
                            <c:forEach var="student" items="${lesson.students}">
                        <li>${student.name} ${student.surname} </li>
                        </c:forEach>
                        </p>

                    </div>
                    <!--/.col-xs-6.col-lg-4-->
                </c:forEach>
            </div>
            <!--/row-->
        </div><!--/.col-xs-12.col-sm-9-->

        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar">
            <div class="list-group">
                <h3>Teachers</h3>
                <c:forEach var="teacher" items="${teachers}">
                    <a href="/controller?currentTeacher=${teacher.id}" class="list-group-item <c:if test="${teacher.id eq currentTeacher}"> active </c:if>">${teacher.name} ${teacher.surname}</a>
                </c:forEach>
            </div>
        </div><!--/.sidebar-offcanvas-->
    </div><!--/row-->

    <hr>

    <footer>
        <p>© 2015</p>
    </footer>

</div><!--/.container-->



<script src="/resources/js/jquery.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>



</body></html>
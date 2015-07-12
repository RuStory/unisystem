<%--
  Created by IntelliJ IDEA.
  User: ru
  Date: 10.07.15
  Time: 2:41
  To change this template use File | Settings | File Templates.
--%>
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

<div class="modal-dialog" role="document">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">New lesson</h4>
        </div>
        <div class="modal-body">
            <form id="lesson-form" action="/controller">
                <div class="form-group">
                    <label class="control-label">Teacher</label>
                    <select class="form-control" name="teacher">
                        <c:forEach var="teacher" items="${teachers}">
                            <option>${teacher.name} ${teacher.surname}</option>
                        </c:forEach>
                    </select>
                    <input type="hidden" name="teacher" id=hiddenTeacher>
                </div>
                <div class="form-group">
                    <label class="control-label">Room</label>
                    <select class="form-control" name="room">
                        <c:forEach var="room" items="${rooms}">
                            <option>${room.number}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label class="control-label">Students</label>
                    <div class="horizontal">
                        <c:forEach var="student" items="${students}">
                            <label class="checkbox-inline">
                                <input type="checkbox" name="studentForLesson${student.id}" value="${student.id}"> ${student.name} ${student.surname}
                            </label>
                        </c:forEach>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label">Name</label>
                    <input type="text" name="name" required="true" class="form-control">
                </div>
                <div class="form-group">
                    <label class="control-label">Time</label>
                    <input type="time" name="time" required="true" class="form-control">
                </div>
                <input type="hidden" id="hiddenButton" name="command">
            </form>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" onclick="
                    document.getElementById('hiddenButton').value='schedule';
                    document.getElementById('lesson-form').submit()">Close</button>
            <button type="button" class="btn btn-primary" onclick="
                    document.getElementById('hiddenButton').value='new_lesson';
                    document.getElementById('lesson-form').submit()">Save</button>
        </div>
    </div>
</div>




<script src="/resources/js/jquery.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>


</body>
</html>
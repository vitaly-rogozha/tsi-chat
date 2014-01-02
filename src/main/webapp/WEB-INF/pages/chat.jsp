<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<title>TSI chat</title>
	<link rel="stylesheet" href="/assets/css/style.css" type="text/css" media="all" />
	<link href='http://fonts.googleapis.com/css?family=Coda' rel='stylesheet' type='text/css' />
	<link href='http://fonts.googleapis.com/css?family=Jura:400,500,600,300' rel='stylesheet' type='text/css' />
	<script src="/assets/js/jquery-1.8.0.min.js" type="text/javascript"></script>
        <script src="/assets/js/slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
        <script src="/assets/js/chat-functions.js" type="text/javascript"></script>
</head>
<body style="background-color:#F0F0F0;">

    <div id="chat">
        <div id="messages">
        </div>
        <div id="users">
            <ul>
            </ul>
        </div>
        <div id="write">
            <input type="text">
        </div>
    </div>
        
</body>
</html>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 22.08.2020
  Time: 10:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>

<head>
    <title> user form</title>

    <style>
        form {
            width: 13%;
            margin: auto;
            border: 5px;
        }
        input {
            float: right;
        }
        .required {
            color: crimson;
        }
    </style>
</head>

<body>

<%@include file="navigation.jsp"%>
<hr>

<form:form action="/jsp_hibernate_project_war_exploded/saveOrUpdateUser" modelAttribute="user" method="post">
    <fieldset>
        <legend><h2><b> User form </b></h2></legend>
        <form:hidden path="id"/>
        name: <form:input path="name" />
        <p>login (*): <form:input path="login" />
                <form:errors path="login" cssClass="required"/>
        <p>password (*): <form:password path="password"/>
                <form:errors path="password" cssClass="required"/>

        <p><button style="height:30px;width:220px"><b> send </b></button></p>
    </fieldset>
</form:form>

</body>

</html>
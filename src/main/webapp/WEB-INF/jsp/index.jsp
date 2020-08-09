<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Login page</title>
    <link href="/res/styles2.css" rel="stylesheet" type="text/css">
  </head>
  <body>
  <h1>First page</h1>
  <div class="form-style-2">

  <div class="form-style-2-heading">
    Please Sign Up!
  </div>
  <form method="post" action="/jsp_hibernate_project_war_exploded/index">
    <label for="login">User login
      <input class="input-field" type="text" id="login" name="login">
    </label>
    <label for="password">Password
      <input class="input-field" type="password" id="password" name="password">
    </label>
    <input type="submit" value="Sign Up">
  </form>



  <form action="/jsp_hibernate_project_war_exploded/admin/read">
    <input type="submit" value="Read User's List" />
  </form>

  <form action="/jsp_hibernate_project_war_exploded/admin/create">
    <input type="submit" value="Create the New User" />
  </form>

  </div>
  </body>
</html>

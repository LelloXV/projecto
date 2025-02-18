<!-- pagina per la gestione di errori -->
<%@ page errorPage="../errors/failure.jsp"%>

<!-- accesso alla sessione -->
<%@ page session="true"%>

<html>
     <head>
		<meta name="Author" content="marco32">
		<title>Login page</title>
		<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/LoginStyles.css" type="text/css"/>
	</head>
	
	<body>
	    <form action="LoginServlet" method="post">
	    <label>Username: </label><br>
	        <input type="text" name="username" placeholder="Username">
	    <label>Password: </label><br>
	        <input type="text" name="password" placeholder="Password">	
	    <input type="submit" name="accedi" value="accedi">
	    </form>
	    
	    <%
	    int tent = (int) this.getServletContext().getAttribute("tentativi");
	    %>
	    <p>Sono rimasti <%=tent%> tentativi</p>
	   
	</body>
</html>
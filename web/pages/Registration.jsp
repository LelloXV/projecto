<!-- pagina per la gestione di errori -->
<%@ page errorPage="../errors/failure.jsp"%>

<!-- accesso alla sessione -->
<%@ page session="true"%>

<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrazione</title>
    <link rel="stylesheet" href="LoginStyles.css">
    <script src="registration.js" defer></script>
</head>
<body>
    <div class="container">
        <div class="card">
            <h2 class="title">Crea il tuo account</h2>
            <form id="registrationForm" action="RegisterServlet" method="post">
                <input type="email" name="email" id="email" placeholder="Email" required>
                <input type="text" name="username" id="username" placeholder="Username" required>
                <input type="password" name="password" id="password" placeholder="Password" required>
                <input type="password" name="confirmPassword" id="confirmPassword" placeholder="Conferma Password" required>
                <p id="error" class="error"></p>
                <button type="submit" class="btn">Registrati</button>
            </form>
            
            <%
            int flag = (int) this.getServletContext().getAttribute("flag");
            if(flag==1){
            %>
            
            <p>Account gia registrato ed esistente. Premere il link per accedere!</p>
            
            <%
            }
            %>
            
            <p class="login-text">
                Hai già un account? <a href="Login.jsp">Accedi</a>
            </p>
        </div>
    </div>
</body>
</html>
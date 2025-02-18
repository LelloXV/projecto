<!-- pagina per la gestione di errori -->
<%@ page errorPage="../errors/failure.jsp"%>

<!-- accesso alla sessione -->
<%@ page session="true"%>

<!-- import di classi Java -->
<%@ page import="java.util.*"%>
<%@ page import="Beans.*"%>

<html>
<head>
    <meta name="Author" content="marco32">
    <title>Crea Nuovo Esercizio</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/styles/styles.css" type="text/css"/>
</head>

<body>
    <div class="container">
        <h2 class="title">Crea un nuovo esercizio</h2>

        <form action="CreaEsercizioServlet" method="post">
            <!-- Nome Esercizio -->
            <label for="nomeEsercizio">Nome Esercizio:</label>
            <input type="text" id="nomeEsercizio" name="nomeEsercizio" required>
            
            <!-- Selezione Categoria -->
            <h3>Seleziona una o più categorie:</h3>
            <label>
                <input type="checkbox" name="categorie" value="Upper"> Upper Body
            </label><br>
            <label>
                <input type="checkbox" name="categorie" value="Core"> Core
            </label><br>
            <label>
                <input type="checkbox" name="categorie" value="Lower"> Lower Body
            </label><br>

            <button type="submit">Crea Esercizio</button>
        </form>
        
        <%
        String err = (String) request.getAttribute("errore");
        if(err !=null){
        %>
        <p><%= err %></p>
        
        <%} %>

        <p class="login-text">
            <a href="sceltaEsercizi.jsp">Torna alla selezione esercizi</a>
        </p>
        
        <br>
        
        <p class="login-text">
            <a href="HomePage.jsp">Torna alla sezione allenamenti</a>
        </p>
    </div>
</body>
</html>
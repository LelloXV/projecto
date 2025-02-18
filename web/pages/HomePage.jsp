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
		<title>HomePage</title>
		<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/styles.css" type="text/css"/>
		
		<script>
        function confermaEliminazione(nome) {
            return confirm("Sei sicuro di voler eliminare l'allenamento '" + nome + "'?");
        }
        </script>
	</head>
	
<body>
	<div class="container">
    
    <% Utente utente = (Utente) request.getSession().getAttribute("user");%>
    
    <h1>Benvenuto, <%= utente.getUsername() %>!</h1>
    
    <%
    if (utente.getAllenamenti().getAllenamenti().isEmpty()) { %>
            <p>Non hai ancora nessun allenamento registrato.</p>
    <% } else { %>
    
    <%
    for(Allenamento a : utente.getAllenamenti().getAllenamenti()){
    %>
    <div class="card">
        <h2 class="title"><%=a.getNome()%> - <%=a.getData()%></h2>
        
        <% if (a.getNote() != null && !a.getNote().trim().isEmpty()) { %>
             <h3>Note:</h3>
             <p><%= a.getNote() %></p>
        <% } %>
                    
        <p class="login-text">
                Visualizza gli esercizi dell'allenamento: <a href="VisualizzaAllenamentoServlet?nome=<%=a.getId() %>">Esercizi</a>
        </p> 
        
        <form action="EliminaAllenamentoServlet" method="post" onsubmit="return confermaEliminazione('<%= a.getNome() %>');">
              <input type="hidden" name="allenamentoId" value="<%= a.getId() %>">
              <button type="submit">Elimina</button>
        </form>
    </div>
    
    <%    }
    }
    %>
    
    <% String success = request.getParameter("success"); 
       if ("eliminato".equals(success)) { %>
        <script>alert("Allenamento eliminato con successo!");</script>
    <% } %>
    
    <p class="login-text">
       <a href="AggiungiAllenamento.jsp">Vuoi aggiungere un nuovo allenamento?</a>
    </p>
    
    <p class="login-text">
       <a href="CreaEsercizio.jsp">Vuoi creare un nuovo esercizio?</a>
    </p>
    
    <p class="login-text">
       <a href="Statistiche.jsp">Visualizza le tue statistiche</a>
    </p>
       
    </div>
    
</body>
	
</html>
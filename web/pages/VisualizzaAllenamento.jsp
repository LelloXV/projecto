<!-- pagina per la gestione di errori -->
<%@ page errorPage="../errors/failure.jsp"%>

<!-- accesso alla sessione -->
<%@ page session="true"%>

<!-- import di classi Java -->
<%@ page import="java.util.*"%>
<%@ page import="Beans.*"%>

<%
    Allenamento allenamento = (Allenamento) request.getSession().getAttribute("allenamentoSelezionato");
    List<Esercizio> esercizi = allenamento.getEsercizi();
%>

<html>
     <head>
		<meta name="Author" content="marco32">
		<title>Dettagli Allenamento</title>
		<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/styles.css" type="text/css"/>
	</head>
	
<body>
	<div class="container">
	
	<h2 class="title">Lista esercizi allenamento: <%= allenamento.getNome() %></h2>
	
	<% if (esercizi != null && !esercizi.isEmpty()) { %>
	<%
	for(Categoria c : Categoria.values()){
	%>
	<h2><%=c.name() %></h2>
	<%
		for(Esercizio e : esercizi){
			if(e.getCategorie().contains(c)){
	%>
	    <div class="card">
	    <p><%=e.getNome() %></p>
	    
	    <form action="ModificaSerieServlet" method="post">
	    <%for(Serie s : e.getSerieTot()){%>
	          <input type="hidden" name="serieId" value="<%= s.getId() %>">
	          <label>Peso:</label>
              <input type="number" name="peso" value="<%= s.getPeso() %>" required/>

              <label>Ripetizioni:</label>
              <input type="number" name="ripetizioni" value="<%= s.getRipetizioni() %>" required/>

              <button type="submit">Salva</button>
              
              </form>
              
              <form action="EliminaSerieServlet" method="post">
              <input type="hidden" name="allenamentoId" value="<%= allenamento.getId() %>">
	          <input type="hidden" name="serieId" value="<%= s.getId() %>">
              <button type="submit">Elimina</button>
              </form>
	    
	    <%
	    }
	    %>
	    
	    <form action="EliminaEsercizioServlet" method="post">
	          <input type="hidden" name="allenamentoId" value="<%= allenamento.getId() %>">
              <input type="hidden" name="esercizioNome" value="<%= e.getNome() %>">
              <button type="submit">Elimina</button>
        </form>
	    </div>
	     <%} else{ %>
	     <p>Nessun esercizio registrato per questo allenamento.</p>    
	<%
	            }
			}
		}
	}
	%>
	
	
	<p class="login-text">
       <a href="sceltaEsercizi.jsp">Vuoi aggiungere un nuovo esercizio?</a>
    </p>
	
	</div>
	
</body>

</html>
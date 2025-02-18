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
		<title>Aggiungi Allenamento</title>
		<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/styles.css" type="text/css"/>
	</head>
	
<body>
	<div class="container">
	    <h2>Inserisci i dati dell'allenamento</h2>
	    
	    <form action="AggiungiAllenamentoServlet" method="post">
	    <label>Nome dell'allenamento:</label>
	    <input type="text" name="allenamentoNome" value="" required/>
	    <label>Data dell'allenamento:</label>
	    <input type="date" name="allenamentoData" value="" required/>
	    <label>Eventuali note:</label>
	    <textarea rows="5" cols="30" name="note" placeholder="Inserisci eventuali note"></textarea>
	    
	    <button type="submit">Conferma</button>
	    
	    </form>
	
	
	</div>
	
</body>	
	
</html>
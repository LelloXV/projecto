<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.*, Beans.*" %>

<%
    Allenamento a = (Allenamento) request.getSession().getAttribute("allenamentoSelezionato");
    Utente utente = (Utente) request.getSession().getAttribute("user");
    List<Esercizio> listaDisp = (List<Esercizio>) this.getServletContext().getAttribute("eserciziDisponibili");
    List<Esercizio> listaEsercizi = new ArrayList<Esercizio>();
    
    if(listaDisp != null && utente.getEserciziCreati() != null){
    for(Esercizio e : listaDisp){
    	listaEsercizi.add(e);
    }
    
    
    for(Esercizio e : utente.getEserciziCreati()){
    	listaEsercizi.add(e);
    }
    }
%>

<html>
<head>
    <meta name="Author" content="marco32">
    <title>Seleziona Esercizio</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/styles/styles.css" type="text/css"/>
    <script>
        function filtraEsercizi() {
            var input = document.getElementById("searchInput").value.toLowerCase();
            var categorie = document.getElementsByClassName("categoria");

            for (let i = 0; i < categorie.length; i++) {
                let esercizi = categorie[i].getElementsByClassName("esercizio");
                let categoriaVisibile = false;

                for (let j = 0; j < esercizi.length; j++) {
                    let nomeEsercizio = esercizi[j].innerText.toLowerCase();
                    if (nomeEsercizio.includes(input)) {
                        esercizi[j].style.display = "block";
                        categoriaVisibile = true;
                    } else {
                        esercizi[j].style.display = "none";
                    }
                }
                categorie[i].style.display = categoriaVisibile ? "block" : "none";
            }
        }
    </script>
</head>

<body>
    <div class="container">
        <h2 class="title">Seleziona un esercizio</h2>

        <!-- Barra di ricerca -->
        <input type="text" id="searchInput" onkeyup="filtraEsercizi()" placeholder="Cerca un esercizio...">

        <form action="AggiungiEsercizioServlet" method="post">
            <% 
                Map<String, List<Esercizio>> eserciziPerCategoria = new HashMap<>();
                eserciziPerCategoria.put("Upper", new ArrayList<>());
                eserciziPerCategoria.put("Core", new ArrayList<>());
                eserciziPerCategoria.put("Lower", new ArrayList<>());

                // Organizza gli esercizi per categoria
                for (Esercizio e : listaEsercizi) {
                    for (Categoria c : e.getCategorie()) {
                        eserciziPerCategoria.get(c.toString()).add(e);
                    }
                }

                // Stampa gli esercizi suddivisi per categoria
                for (String categoria : eserciziPerCategoria.keySet()) {
                    List<Esercizio> eserciziCategoria = eserciziPerCategoria.get(categoria);
                    if (!eserciziCategoria.isEmpty()) {
            %>
            <input type="hidden" name="allenamentoId" value="<%= a.getId() %>" />
                <div class="categoria">
                    <h3><%= categoria %></h3>
                    <% for (Esercizio e : eserciziCategoria) { %>
                        <label class="esercizio">
                            <input type="radio" name="esercizioScelto" value="<%= e.getNome() %>"> <%= e.getNome() %>
                        </label><br>
                    <% } %>
                </div>
            <% } } %>

            <button type="submit">Aggiungi Esercizio</button>
        </form>

        <p class="login-text">
            <a href="CreaEsercizio.jsp">Non trovi l'esercizio? Creane uno nuovo</a>
        </p>
    </div>
</body>
</html>
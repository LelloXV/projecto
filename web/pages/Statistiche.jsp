<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, Beans.*" %>
<%@ page session="true" %>
<%
    // Recupera l'utente dalla sessione
    Utente user = (Utente) session.getAttribute("utente");
    if (user == null) {
        response.sendRedirect("login.jsp"); // Se non autenticato, reindirizza al login
        return;
    }

    // Recupera i dati della richiesta
    List<StatisticaEsercizio> stats = (List<StatisticaEsercizio>) request.getAttribute("statistiche");
    boolean datiDisponibili = (stats != null && !stats.isEmpty());
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Statistiche Utente</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <h2>Statistiche di <%= user.getUsername() %></h2>

    <!-- Form di inserimento intervallo temporale -->
    <form action="StatisticheServlet" method="post">
        <label for="inizio">Data Inizio:</label>
        <input type="date" id="inizio" name="inizio" required>
        <label for="fine">Data Fine:</label>
        <input type="date" id="fine" name="fine" required>
        <button type="submit">Conferma</button>
    </form>

    <% if (datiDisponibili) { %>
        <table border="1">
            <tr>
                <th>Esercizio</th>
                <th>Massimale Teorico Migliore</th>
                <th>Massimale Effettivo Migliore</th>
                <th>Grafico Massimali</th>
            </tr>
            <% for (StatisticaEsercizio stat : stats) { %>
            <tr>
                <td><%= stat.getEsercizio().getNome() %></td>
                <td><%= stat.getMassimaleTeorico() %> kg</td>
                <td><%= stat.getMassimaleEffettivo() %> kg</td>
                <td>
                    <canvas id="chart-<%= stat.getEsercizio().getNome().replaceAll("\\s+", "") %>" width="400" height="200"></canvas>
                </td>
            </tr>
            <% } %>
        </table>

        <script>
            <% for (StatisticaEsercizio stat : stats) { %>
                var ctx = document.getElementById("chart-<%= stat.getEsercizio().getNome().replaceAll("\\s+", "") %>").getContext("2d");
                var chart = new Chart(ctx, {
                    type: 'line',
                    data: {
                        labels: [<%
                            for (Date date : stat.getDataTeorico().keySet()) {
                                out.print("'" + date.toString() + "',");
                            }
                        %>],
                        datasets: [{
                            label: 'Massimale Teorico',
                            borderColor: 'blue',
                            fill: false,
                            data: [<%
                                for (Float value : stat.getDataTeorico().values()) {
                                    out.print(value + ",");
                                }
                            %>]
                        },
                        {
                            label: 'Massimale Effettivo',
                            borderColor: 'red',
                            fill: false,
                            data: [<%
                                for (Float value : stat.getDataEffettivo().values()) {
                                    out.print(value + ",");
                                }
                            %>]
                        }]
                    },
                    options: {
                        responsive: true,
                        scales: {
                            x: { title: { display: true, text: 'Data' } },
                            y: { title: { display: true, text: 'Massimale (kg)' } }
                        }
                    }
                });
            <% } %>
        </script>
    <% } else { %>
        <p>Inserisci una data di inizio e fine per visualizzare le statistiche.</p>
    <% } %>

</body>
</html>

package Servlets;

import Beans.*;
import java.io.IOException;
import java.util.*;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class StatisticheServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente user = (Utente) session.getAttribute("utente");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date inizio = sdf.parse(request.getParameter("inizio"));
            Date fine = sdf.parse(request.getParameter("fine"));

            Statistiche statistiche = new Statistiche(inizio, fine, user, new ArrayList<>());
            List<StatisticaEsercizio> stats = new ArrayList<>();

            List<EsercizioUtente> esercizi_utente = user.getEserciziCreati();
            
            for (Esercizio e : esercizi_utente) {
                StatisticaEsercizio stat = new StatisticaEsercizio(e);
                stat.setMassimaleTeorico(statistiche.calcolaBestMassimaleTeorico(e));
                stat.setMassimaleEffettivo(statistiche.calcolaBestMassimaleEffettivo(e));
                stat.setDataTeorico(statistiche.ottieniDatiGraficoTeorico(e));
                stat.setDataEffettivo(statistiche.ottieniDatiGraficoEffettivo(e));
                stats.add(stat);
            }
            
            List<Esercizio> DBEsercizi = (List<Esercizio>) this.getServletContext().getAttribute("eserciziDisponibili");
            
            for (Esercizio e : esercizi_utente) {
                StatisticaEsercizio stat = new StatisticaEsercizio(e);
                stat.setMassimaleTeorico(statistiche.calcolaBestMassimaleTeorico(e));
                stat.setMassimaleEffettivo(statistiche.calcolaBestMassimaleEffettivo(e));
                stat.setDataTeorico(statistiche.ottieniDatiGraficoTeorico(e));
                stat.setDataEffettivo(statistiche.ottieniDatiGraficoEffettivo(e));
                stats.add(stat);
            }

            request.setAttribute("statistiche", stats);
            request.getRequestDispatcher("Statistiche.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("Statistiche.jsp");
        }
    }
}

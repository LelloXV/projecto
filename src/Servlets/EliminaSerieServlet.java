package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.Esercizio;
import Beans.Serie;
import Beans.Utente;

public class EliminaSerieServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		int Id = Integer.parseInt(request.getParameter("serieId"));
		int allenamentoId = Integer.parseInt(request.getParameter("allenamentoId"));
		Utente utente = (Utente) request.getSession().getAttribute("user");
		
		if (utente == null) {
            response.sendRedirect("Login.jsp"); // Se l'utente non è loggato, reindirizza al login
            return;
        }
		
		if(utente != null) {
			for(Esercizio e : utente.getAllenamenti().getAllenamentoById(allenamentoId).getEsercizi()) {
				for(Serie s : e.getSerieTot()) {
					if(s.getId() == Id)
						e.eliminaSerie(s);
				}
			}
		}
		
		request.getSession().setAttribute("user", utente);
		response.sendRedirect("VisualizzaAllenamento.jsp");
		
	}
}

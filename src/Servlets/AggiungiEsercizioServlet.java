package Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Beans.Esercizio;
import Beans.Utente;

public class AggiungiEsercizioServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Utente utente = (Utente) req.getSession().getAttribute("user");
		
		if (utente == null) {
            resp.sendRedirect("Login.jsp"); // Se l'utente non è loggato, reindirizza al login
            return;
        }
		
		String nomeEsercizio = (String) req.getAttribute("esercizioScelto");
		List<Esercizio> lista = (List<Esercizio>) this.getServletContext().getAttribute("eserciziDisponibili");
		int allenamentoId = Integer.parseInt(req.getParameter("allenamentoId"));
		List<Esercizio> disp = new ArrayList<Esercizio>();
		
		for(Esercizio e : lista) {
			disp.add(e);
		}
		
		for(Esercizio e : utente.getEserciziCreati()) {
			disp.add(e);
		}
		
		if(utente != null) {
			for(Esercizio e : disp) {
				if(e.getNome().equals(nomeEsercizio)) {
					utente.getAllenamenti().getAllenamentoById(allenamentoId).aggiungiEsercizio(e);
				}		
			}
		}
		
		this.getServletContext().setAttribute("user", utente);
		resp.sendRedirect("VisualizzaAllenamento.jsp");
		
	}
}

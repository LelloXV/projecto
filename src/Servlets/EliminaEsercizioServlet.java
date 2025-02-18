package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.Esercizio;
import Beans.Utente;

public class EliminaEsercizioServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		String nome = request.getParameter("esercizioNome");
		Utente utente = (Utente) request.getSession().getAttribute("user");
		
		if (utente == null) {
            response.sendRedirect("Login.jsp"); // Se l'utente non è loggato, reindirizza al login
            return;
        }
		
		int allenamentoId = Integer.parseInt(request.getParameter("allenamentoId"));

        if (utente != null) {
        	for(Esercizio e : utente.getAllenamenti().getAllenamentoById(allenamentoId).getEsercizi()) {
        		if(e.getNome().equals(nome))
        			utente.getAllenamenti().getAllenamentoById(allenamentoId).eliminaEsercizio(e);
        	}
        }
        
        request.getSession().setAttribute("user", utente);
        response.sendRedirect("HomePage.jsp");
	}
}

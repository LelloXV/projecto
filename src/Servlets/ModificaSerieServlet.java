package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.Allenamento;
import Beans.Esercizio;
import Beans.Serie;
import Beans.Utente;

public class ModificaSerieServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Utente utente = (Utente) request.getSession().getAttribute("user");
		
		if (utente == null) {
            response.sendRedirect("Login.jsp"); // Se l'utente non è loggato, reindirizza al login
            return;
        }
		
		try {
			
			int serieId = Integer.parseInt(request.getParameter("serieId"));
            float nuovoPeso = Float.parseFloat(request.getParameter("peso"));
            int nuoveRipetizioni = Integer.parseInt(request.getParameter("ripetizioni"));
            
            boolean serieTrovata = false;
            
            for(Allenamento a : utente.getAllenamenti().getAllenamenti()) {
            	for(Esercizio e : a.getEsercizi()) {
            		for(Serie s : e.getSerieTot()) {
            			if(s.getId() == serieId) {
            				s.setPeso(nuovoPeso);
                            s.setRipetizioni(nuoveRipetizioni);
                            serieTrovata = true;
                            break;
            			}
            		}
            		if (serieTrovata) break;
            	}
            	if (serieTrovata) break;
            }
            
            request.getSession().setAttribute("user", utente);
            response.sendRedirect("visualizzaAllenamento.jsp");
            
		}catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Formato dei dati non valido.");
            request.getRequestDispatcher("visualizzaAllenamento.jsp").forward(request, response);
        }
	}

}

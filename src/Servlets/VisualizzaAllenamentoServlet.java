package Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.Allenamento;
import Beans.Utente;

public class VisualizzaAllenamentoServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		Utente utente = (Utente) request.getSession().getAttribute("user");
		
		if (utente == null) {
            response.sendRedirect("Login.jsp"); // Se l'utente non è loggato, reindirizza al login
            return;
        }
		
		int id = (int) request.getAttribute("nome");
		
		if (utente != null && utente.getAllenamenti() != null) {
			Allenamento a = utente.getAllenamenti().getAllenamentoById(id);
			if(a != null) {
				request.getSession().setAttribute("allenamentoSelezionato", a);
			}
			
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("VisualizzaAllenamento.jsp");
        dispatcher.forward(request, response);
	}
}

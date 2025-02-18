package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.Utente;

public class EliminaAllenamentoServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

        int allenamentoId = Integer.parseInt(request.getParameter("allenamentoId"));
        Utente utente = (Utente) request.getSession().getAttribute("user");
        
        if (utente == null) {
            response.sendRedirect("Login.jsp"); // Se l'utente non è loggato, reindirizza al login
            return;
        }
        
        boolean res = true;

        if (utente != null) {
        	res = utente.getAllenamenti().eliminaAllenamento(utente.getAllenamenti().getAllenamentoById(allenamentoId));
        }
        
        request.getSession().setAttribute("user", utente);

        if(res)
        	response.sendRedirect("HomePage.jsp?success=eliminato");
        else
        	response.sendRedirect("HomePage.jsp?success=NotEliminato");
    }
}

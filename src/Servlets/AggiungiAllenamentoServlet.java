package Servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.Allenamento;
import Beans.Esercizio;
import Beans.Utente;

public class AggiungiAllenamentoServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		Utente user = (Utente) request.getSession().getAttribute("user");
		
		if (user == null) {
            response.sendRedirect("Login.jsp"); // Se l'utente non è loggato, reindirizza al login
            return;
        }
		
		String nome = request.getParameter("allenamentoNome");
		Date d = new Date();
		try {
			d = DateFormat.getInstance().parse(request.getParameter("allenamentoData"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String note = request.getParameter("note");
		
		Allenamento a = new Allenamento(nome,d,new ArrayList<Esercizio>(),note);
		
		user.getAllenamenti().aggiungiAllenamento(a);
		request.getSession().setAttribute("user", user);
		
		RequestDispatcher disp = this.getServletContext().getRequestDispatcher("/HomePage.jsp");
		disp.forward(request, response);
	}

}

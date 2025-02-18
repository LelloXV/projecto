package Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.Utente;

public class RegistrationServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig conf) throws ServletException {
		super.init(conf);	
		
		List<Utente> DBFake = new ArrayList<Utente>();
		this.getServletContext().setAttribute("DB", DBFake);
		this.getServletContext().setAttribute("flag", 0);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String email = request.getParameter("email");
	    String username = request.getParameter("username");
	    String password = request.getParameter("password");
	    Utente user = new Utente(username,password,email);

	    // Logica fake per salvare l'utente nel database.
	    List<Utente> DB = (List<Utente>) this.getServletContext().getAttribute("DB");
	    if(DB.contains(user)) {
	    	this.getServletContext().setAttribute("flag", 1);
	    	RequestDispatcher d1 = this.getServletContext().getRequestDispatcher("/Registration.jsp");
			d1.forward(request, response);
	    }
	    else {
	    DB.add(user);
	    }
	    
	    RequestDispatcher d = this.getServletContext().getRequestDispatcher("/succes.jsp");
		d.forward(request, response);
	}

}

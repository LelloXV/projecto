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

import Beans.Categoria;
import Beans.Esercizio;
import Beans.EsercizioGenerale;
import Beans.Serie;
import Beans.Utente;

public class LoginServlet extends HttpServlet{

	private static final long serialVersionUID = 3157962322160824370L;

	public void init(ServletConfig conf) throws ServletException {
		super.init(conf);
		
		int tentativi=3;
		this.getServletContext().setAttribute("tentativi", tentativi);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String username = req.getParameter("username");
		String pwd = req.getParameter("password");
		int cont=0;
		
		List<Utente> DB = (List<Utente>) this.getServletContext().getAttribute("DB");
		for(Utente u : DB) {
			if(u.getUsername().equals(username)) {
				if(u.getPassword().equals(pwd)) {
					cont++;
					req.getSession().setAttribute("user", u);
					break;
				}
			}
		}
		
		if(cont==1) {
			RequestDispatcher d = this.getServletContext().getRequestDispatcher("/HomePage.jsp");
			d.forward(req, resp);
		}
		else {
			int tentativi=(int) this.getServletContext().getAttribute("tentativi");
			tentativi--;
			req.getSession().setAttribute("tentativi", tentativi);
			if(tentativi==0) {
				//Logica per mandare la mail col codice
			}
			else {
				RequestDispatcher d = this.getServletContext().getRequestDispatcher("/Login.jsp");
				d.forward(req, resp);
			}
		}
	}
}

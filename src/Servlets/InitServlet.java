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

public class InitServlet extends HttpServlet{

	public void init(ServletConfig conf) throws ServletException {
		super.init(conf);
		
		List<Esercizio> DBEsercizi = new ArrayList<Esercizio>();
		List<Serie> s = new ArrayList<Serie>();
		for(int i=1; i<6;i++) {
			List<Categoria> c = new ArrayList<Categoria>();
			c.add(Categoria.CORE);
			DBEsercizi.add(new EsercizioGenerale("Esercizio"+i,c, s));
		}
		
		for(int i=6; i<11;i++) {
			List<Categoria> c = new ArrayList<Categoria>();
			c.add(Categoria.LOWER);
			c.add(Categoria.UPPER);
			DBEsercizi.add(new EsercizioGenerale("Esercizio"+i,c, s));
		}
		
		for(int i=11; i<16;i++) {
			List<Categoria> c = new ArrayList<Categoria>();
			c.add(Categoria.UPPER);
			DBEsercizi.add(new EsercizioGenerale("Esercizio"+i,c, s));
		}
		
		this.getServletContext().setAttribute("eserciziDisponibili", DBEsercizi);
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher d = this.getServletContext().getRequestDispatcher("/HomePage.jsp");
		d.forward(req, resp);
	}
}

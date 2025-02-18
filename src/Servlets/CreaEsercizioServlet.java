package Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.Categoria;
import Beans.Esercizio;
import Beans.EsercizioUtente;
import Beans.Serie;
import Beans.Utente;

public class CreaEsercizioServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		Utente utente = (Utente) request.getSession().getAttribute("user");
		
		if (utente == null) {
            response.sendRedirect("Login.jsp"); // Se l'utente non è loggato, reindirizza al login
            return;
        }
		
		String nomeEsercizio = request.getParameter("nomeEsercizio");
		String[] categorieSelezionate = request.getParameterValues("categorie");
		List<Esercizio> disp = (List<Esercizio>) this.getServletContext().getAttribute("eserciziDisponibili");
		int cont=0;
		
		if (nomeEsercizio == null || nomeEsercizio.trim().isEmpty() || categorieSelezionate == null) {
            request.setAttribute("errore", "Inserisci un nome valido e seleziona almeno una categoria.");
            request.getRequestDispatcher("CreaEsercizio.jsp").forward(request, response);
            return;
        }
		
		List<Categoria> categorie = new ArrayList<Categoria>();
        for (String cat : categorieSelezionate) {
            categorie.add(Categoria.valueOf(cat)); 
        }
        
        for(Esercizio e : disp) {
        	if(e.getNome().equals(nomeEsercizio))
        		cont++;
        }
        
        for(Esercizio e : utente.getEserciziCreati()) {
        	if(e.getNome().equals(nomeEsercizio))
        		cont++;
        }
        
        if(cont==0) {
        	utente.creaEsercizio(nomeEsercizio, categorie);
            request.getSession().setAttribute("user", utente);
        }else {
        	request.setAttribute("errore", "Inserisci un nome valido, quello inserito è gia esistente");
        }
        
        response.sendRedirect("HomePage.jsp");
	}

}

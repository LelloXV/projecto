package Beans;

import java.util.List;

public class EsercizioUtente extends Esercizio{

	public Utente utente;

	public EsercizioUtente(String nome,List<Categoria> c, List<Serie> s, Utente u) {
		super(nome,c,s);
		this.utente=u;
	}
	
	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}
	
}

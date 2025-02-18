package Beans;

import java.util.ArrayList;
import java.util.List;

public class Utente {

	public String username;
	public String password;
	public String email;
	public boolean isGestoreSicurezza;
	public Allenamenti allenamenti;
	//forse da togliere
	public List<EsercizioUtente> eserciziCreati;
	
	public Utente(String username2, String password2, String email2) {
		this.username=username2;
		this.password=password2;
		this.email=email2;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean getIsGestoreSicurezza() {
		return false;
	}
	
	public Allenamenti getAllenamenti() {
		return allenamenti;
	}
	
	public void setAllenamenti(Allenamenti allenamenti) {
		this.allenamenti = allenamenti;
	}
	
	public List<EsercizioUtente> getEserciziCreati(){
		return eserciziCreati;
	}
	
	public boolean creaEsercizio(String nome,List<Categoria> c) {
		EsercizioUtente e = new EsercizioUtente(nome,c, new ArrayList<Serie>(), this);
		return this.eserciziCreati.add(e);
	}
		
}

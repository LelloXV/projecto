package Beans;

import java.util.List;

public abstract class Esercizio {

	public String nome;
	public List<Categoria> categorie;
	public List<Serie> serieTot;
	
	public Esercizio(String n, List<Categoria> c, List<Serie> s) {
		this.nome=n;
		this.categorie=c;
		this.serieTot=s;
	}
	
	public Esercizio() {
		
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<Categoria> getCategorie() {
		return categorie;
	}
	
	public void setCategorie(List<Categoria> categorie) {
		this.categorie = categorie;
	}
	
	public List<Serie> getSerieTot() {
		return serieTot;
	}
	
	public void setSerieTot(List<Serie> serieTot) {
		this.serieTot = serieTot;
	}
	
	public boolean aggiungiSerie(Serie s) {
		return this.getSerieTot().add(s);
	}
	
	public boolean eliminaSerie(Serie s) {
		return this.getSerieTot().remove(s);
	}
	
	public float ottieniMassimaleTeoricoEsercizio() {
		float max=0;
		
		for(Serie s : serieTot) {
			if(s.calcolaMassimaleTeoricoSerie() > max)
				max = s.calcolaMassimaleTeoricoSerie();
		}
		
		return max;
	}
	
	public float ottieniMassimaleEffettivoEsercizio() {
		float max=0;
		
		for(Serie s : serieTot) {
			if(s.calcolaMassimaleTeoricoSerie() > max)
				max = s.calcolaMassimaleEffettivoSerie();
		}
		
		return max;
	}
}

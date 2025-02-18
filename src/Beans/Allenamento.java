package Beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Allenamento {

	public List<Esercizio> esercizi;
	public String nome;
	public Date data;
	public String note;
	public int id;
	
	public Allenamento(String n,Date d, List<Esercizio> e,String no) {
		this.nome=n;
		this.data=d;
		this.esercizi=e;
		this.note=no;
		this.id=(int) Math.random();
	}
	
	public Allenamento(String n,Date d, List<Esercizio> e) {
		this.nome=n;
		this.data=d;
		this.esercizi=e;
		this.note="";
	}
	
	public Allenamento() {
		
	}
	
	public int getId() {
		return id;
	}
	
	public List<Esercizio> getEsercizi() {
		return esercizi;
	}
	
	public void setEsercizi(List<Esercizio> esercizi) {
		this.esercizi = esercizi;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}
	
	public String getNote() {
		return note;
	}
	
	public void setNote(String note) {
		this.note = note;
	}
	
	public boolean aggiungiEsercizio(Esercizio e) {
		return this.esercizi.add(e);
	}
	
	public boolean eliminaEsercizio(Esercizio e) {
		return this.esercizi.remove(e);
	}
	
	public Map<Categoria,List<Esercizio>> getEserciziByCategoria(){
		Map<Categoria,List<Esercizio>> res = new HashMap<Categoria,List<Esercizio>>();
		List<Esercizio> core = new ArrayList<Esercizio>();
		List<Esercizio> low = new ArrayList<Esercizio>();
		List<Esercizio> up = new ArrayList<Esercizio>();
		
		for(Esercizio e : esercizi) {
			if(e.getCategorie().contains(Categoria.CORE)) {
				core.add(e);
			}
			else if(e.getCategorie().contains(Categoria.LOWER)) {
				low.add(e);
			}
			else if(e.getCategorie().contains(Categoria.UPPER)) {
				up.add(e);
			}
		}
		
		res.put(Categoria.CORE, core);
		res.put(Categoria.LOWER, low);
		res.put(Categoria.UPPER, up);
		
		return res;
	}
	/*
	public boolean creaEsercizio(String nome,List<Categoria> c) {
		Esercizio e = new EsercizioUtente(nome,c, new ArrayList<Serie>(), this.utente);
		return this.esercizi.add(e);
	}
	*/
}

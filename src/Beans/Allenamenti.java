package Beans;

import java.util.List;

public class Allenamenti {
	
	public List<Allenamento> all;
	
	public Allenamenti(List<Allenamento> a) {
		this.all=a;
	}
	
	public List<Allenamento> getAllenamenti() {
		return all;
	}
	
	public void setAllenamenti(List<Allenamento> all) {
		this.all = all;
	}
	
	public boolean aggiungiAllenamento(Allenamento a) {
		return all.add(a);
	}
	
	public boolean eliminaAllenamento(Allenamento a) {
		return all.remove(a);
	}
	
	public Allenamento getAllenamentoByName(String n) {
		for(Allenamento a : all) {
			if(a.getNome().equals(n))
				return a;
		}
		return null;
	}
	
	public Allenamento getAllenamentoById(int id) {
		for(Allenamento a : all) {
			if(a.getId()==id)
				return a;
		}
		return null;
	}

}

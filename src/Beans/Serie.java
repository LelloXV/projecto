package Beans;

public class Serie {
	
	public int id;
	public float peso;
	public int ripetizioni;

	public Serie(float i, int j) {
		this.id=(int) Math.random();
		this.peso=i;
		this.ripetizioni=j;
	}

	public Serie() {
	}
	
	public int getId() {
		return id;
	}

	public float getPeso() {
		return peso;
	}

	public boolean setPeso(float peso) {
		this.peso = peso;
		if(this.peso==peso)
			return true;
		else
			return false;
	}

	public int getRipetizioni() {
		return ripetizioni;
	}

	public boolean setRipetizioni(int ripetizioni) {
		this.ripetizioni = ripetizioni;
		if(this.ripetizioni==ripetizioni)
			return true;
		else
			return false;
	}

	public float calcolaMassimaleEffettivoSerie() {
		if(ripetizioni == 1)
			return peso;
		else
			return (float) 0;
	}
	
	public float calcolaMassimaleTeoricoSerie() {
		return (float) (peso/(1.0278-(0.0278*ripetizioni)));	
	}

}

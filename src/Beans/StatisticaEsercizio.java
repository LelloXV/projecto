package Beans;

import java.util.Date;
import java.util.Map;

public class StatisticaEsercizio {
	
    private Esercizio esercizio;
    private float massimaleTeorico;
    private float massimaleEffettivo;
    private Map<Date,Float> dataTeorico;
    private Map<Date,Float> dataEffettivo;

    public StatisticaEsercizio(Esercizio esercizio,float massimaleTeorico, float massimaleEffettivo,Map<Date,Float> dataTeorico,Map<Date,Float> dataEffettivo ) {
        this.esercizio = esercizio;
        this.massimaleTeorico = massimaleTeorico;
        this.massimaleEffettivo = massimaleEffettivo;
        this.dataEffettivo = dataEffettivo;
        this.dataTeorico = dataTeorico;
    }
    
    public StatisticaEsercizio(Esercizio es) {
    	this.esercizio=es;
    }

	public Esercizio getEsercizio() {
		return esercizio;
	}

	public void setEsercizio(Esercizio esercizio) {
		this.esercizio = esercizio;
	}

	public float getMassimaleTeorico() {
		return massimaleTeorico;
	}

	public void setMassimaleTeorico(float massimaleTeorico) {
		this.massimaleTeorico = massimaleTeorico;
	}

	public float getMassimaleEffettivo() {
		return massimaleEffettivo;
	}

	public void setMassimaleEffettivo(float massimaleEffettivo) {
		this.massimaleEffettivo = massimaleEffettivo;
	}

	public Map<Date, Float> getDataTeorico() {
		return dataTeorico;
	}

	public void setDataTeorico(Map<Date, Float> dataTeorico) {
		this.dataTeorico = dataTeorico;
	}

	public Map<Date, Float> getDataEffettivo() {
		return dataEffettivo;
	}

	public void setDataEffettivo(Map<Date, Float> dataEffettivo) {
		this.dataEffettivo = dataEffettivo;
	}
    
}
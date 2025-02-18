package Beans;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Statistiche {
	
	public Date inizio;
	public Date fine;
	public Utente user;
	public List<StatisticaEsercizio> stats;
	
	public Statistiche(Date inizio, Date fine, Utente user, List<StatisticaEsercizio> st) {
		this.fine= fine;
		this.inizio=inizio;
		this.user=user;
		this.stats=st;
	}

	public Date getInizio() {
		return inizio;
	}

	public void setInizio(Date inizio) {
		this.inizio = inizio;
	}

	public Date getFine() {
		return fine;
	}

	public void setFine(Date fine) {
		this.fine = fine;
	}

	public Utente getUser() {
		return user;
	}

	public void setUser(Utente user) {
		this.user = user;
	}

	public List<StatisticaEsercizio> getStats() {
		return stats;
	}

	public void setStats(List<StatisticaEsercizio> stats) {
		this.stats = stats;
	}
	
	public boolean setIntervalloTemporale(Date i, Date f) {
		this.inizio=i;
		this.fine=f;
		if(this.inizio==i && this.fine==f)
			return true;
		else
			return false;
	}
	
	public Map<Date,Float> ottieniDatiGraficoEffettivo(Esercizio e){
		Map<Date,Float> data = new HashMap<Date,Float>();
		
		for(StatisticaEsercizio st : stats) {
			if(st.getEsercizio().equals(e)) {
				for(Allenamento a : user.getAllenamenti().getAllenamenti()) {
					if(a.data.before(fine) && a.data.after(inizio)) {
						for(Esercizio es : a.getEsercizi()) {
							if(es.equals(e)) {
								data.put(a.getData(), es.ottieniMassimaleEffettivoEsercizio());
							}
						}
					}
				}
				st.setDataEffettivo(data);
			}
			
		}
		return data;
	}
	
	public Map<Date,Float> ottieniDatiGraficoTeorico(Esercizio e){
		Map<Date,Float> data = new HashMap<Date,Float>();
		
		for(StatisticaEsercizio st : stats) {
			if(st.getEsercizio().equals(e)) {
				for(Allenamento a : user.getAllenamenti().getAllenamenti()) {
					if(a.data.before(fine) && a.data.after(inizio)) {
						for(Esercizio es : a.getEsercizi()) {
							if(es.equals(e)) {
								data.put(a.getData(), es.ottieniMassimaleTeoricoEsercizio());
							}
						}
					}
				}
				st.setDataTeorico(data);
			}
			
		}
		return data;
	}
	
	public float calcolaBestMassimaleTeorico(Esercizio e) {
		float max=0;
		
		for(StatisticaEsercizio st : stats) {
			if(st.getEsercizio().equals(e)) {
				for(Allenamento a : user.getAllenamenti().getAllenamenti()) {
					if(a.data.before(fine) && a.data.after(inizio)) {
						for(Esercizio es : a.getEsercizi()) {
							if(es.equals(e)) {
								if(es.ottieniMassimaleTeoricoEsercizio()> max)
									max = es.ottieniMassimaleTeoricoEsercizio();
							}
						}
					}
				}
				if(max > st.getMassimaleTeorico())
					st.setMassimaleTeorico(max);
			}
		}
		return max;
	}
	
	public float calcolaBestMassimaleEffettivo(Esercizio e) {
		float max=0;
		
		for(StatisticaEsercizio st : stats) {
			if(st.getEsercizio().equals(e)) {
				for(Allenamento a : user.getAllenamenti().getAllenamenti()) {
					if(a.data.before(fine) && a.data.after(inizio)) {
						for(Esercizio es : a.getEsercizi()) {
							if(es.equals(e)) {
								if(es.ottieniMassimaleEffettivoEsercizio()> max)
									max = es.ottieniMassimaleEffettivoEsercizio();
							}
						}
					}
				}
				if(max > st.getMassimaleEffettivo())
					st.setMassimaleEffettivo(max);
			}
		}
		return max;
	}
	
	
}

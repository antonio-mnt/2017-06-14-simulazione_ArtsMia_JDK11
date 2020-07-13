package it.polito.tdp.artsmia.model;

public class Event implements Comparable<Event>{
	
	public enum EventType{
		PROSSIMA_MOSTRA
	}
	
	private EventType type;
	private Mostra mostra;
	private Studente studente;
	private Integer anno;

	
	public Event(EventType type, Mostra mostra, Studente studente, Integer anno) {
		super();
		this.type = type;
		this.mostra = mostra;
		this.studente = studente;
		this.anno = anno;
	}


	public EventType getType() {
		return type;
	}


	public void setType(EventType type) {
		this.type = type;
	}


	public Mostra getMostra() {
		return mostra;
	}


	public void setMostra(Mostra mostra) {
		this.mostra = mostra;
	}


	public Studente getStudente() {
		return studente;
	}


	public void setStudente(Studente studente) {
		this.studente = studente;
	}


	public Integer getAnno() {
		return anno;
	}


	public void setAnno(Integer anno) {
		this.anno = anno;
	}


	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((studente == null) ? 0 : studente.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (studente == null) {
			if (other.studente != null)
				return false;
		} else if (!studente.equals(other.studente))
			return false;
		return true;
	}


	@Override
	public int compareTo(Event o) {
		return this.anno.compareTo(o.anno);
	}


	@Override
	public String toString() {
		return "Event [type=" + type + ", mostra=" + mostra + ", studente=" + studente + ", anno=" + anno + "]";
	}
	
	

}

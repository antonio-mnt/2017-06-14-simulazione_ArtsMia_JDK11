package it.polito.tdp.artsmia.model;

import java.util.ArrayList;
import java.util.List;

public class Studente implements Comparable<Studente>{
	
	private Integer s;
	private List<Integer> opere;

	public Studente(Integer s) {
		super();
		this.s = s;
		this.opere = new ArrayList<>();
	}
	
	public void AggiungiOpera(Integer op) {
		if(opere.contains(op)) {
			
		}else {
			this.opere.add(op);
		}
	}
	
	public Integer opereViste() {
		return this.opere.size();
	}

	public Integer getS() {
		return s;
	}

	public void setS(Integer s) {
		this.s = s;
	}

	public List<Integer> getOpere() {
		return opere;
	}

	public void setOpere(List<Integer> opere) {
		this.opere = opere;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((s == null) ? 0 : s.hashCode());
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
		Studente other = (Studente) obj;
		if (s == null) {
			if (other.s != null)
				return false;
		} else if (!s.equals(other.s))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Studente [s=" + s + ", opere=" + this.opereViste() + "]";
	}

	@Override
	public int compareTo(Studente o) {
		return -this.opereViste().compareTo(o.opereViste());
	}
	
	

}

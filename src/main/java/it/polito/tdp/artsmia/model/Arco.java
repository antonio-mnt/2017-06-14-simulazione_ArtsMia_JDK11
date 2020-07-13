package it.polito.tdp.artsmia.model;

public class Arco {
	
	private Mostra m1;
	private Mostra m2;
	public Arco(Mostra m1, Mostra m2) {
		super();
		this.m1 = m1;
		this.m2 = m2;
	}
	public Mostra getM1() {
		return m1;
	}
	public void setM1(Mostra m1) {
		this.m1 = m1;
	}
	public Mostra getM2() {
		return m2;
	}
	public void setM2(Mostra m2) {
		this.m2 = m2;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((m1 == null) ? 0 : m1.hashCode());
		result = prime * result + ((m2 == null) ? 0 : m2.hashCode());
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
		Arco other = (Arco) obj;
		if (m1 == null) {
			if (other.m1 != null)
				return false;
		} else if (!m1.equals(other.m1))
			return false;
		if (m2 == null) {
			if (other.m2 != null)
				return false;
		} else if (!m2.equals(other.m2))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Arco [m1=" + m1 + ", m2=" + m2 + "]";
	}
	
	

}

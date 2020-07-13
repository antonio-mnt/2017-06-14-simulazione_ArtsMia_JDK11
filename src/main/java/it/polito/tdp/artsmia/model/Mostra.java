package it.polito.tdp.artsmia.model;

public class Mostra {
	
	private int id;
	private String department;
	private String title;
	private int inizio;
	private int fine;
	
	public Mostra(int id, String department, String title, int inizio, int fine) {
		super();
		this.id = id;
		this.department = department;
		this.title = title;
		this.inizio = inizio;
		this.fine = fine;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getInizio() {
		return inizio;
	}

	public void setInizio(int inizio) {
		this.inizio = inizio;
	}

	public int getFine() {
		return fine;
	}

	public void setFine(int fine) {
		this.fine = fine;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Mostra other = (Mostra) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Mostra [id=" + id + ", department=" + department + ", title=" + title + ", inizio=" + inizio + ", fine="
				+ fine + "]";
	}
	
	

}

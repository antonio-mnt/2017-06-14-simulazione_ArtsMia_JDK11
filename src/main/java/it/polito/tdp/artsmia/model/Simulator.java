package it.polito.tdp.artsmia.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import it.polito.tdp.artsmia.db.ArtsmiaDAO;
import it.polito.tdp.artsmia.model.Event.EventType;


public class Simulator {
	
	private PriorityQueue<Event> queue = new PriorityQueue<>();
	
	private ArtsmiaDAO dao;
	
	private List<Studente> studenti;
	
	private SimpleDirectedGraph<Mostra, DefaultEdge> grafo;
	
	public void simula(int k, SimpleDirectedGraph<Mostra, DefaultEdge> g, List<Mostra> mostre) {
		
		this.grafo = g;
		
		this.dao = new ArtsmiaDAO();
		
		this.studenti = new ArrayList<>();
		
		for(int i = 0; i<k; i++) {
			this.studenti.add(new Studente(i));
		}
		
		this.queue.clear();
		Mostra m = this.estraiMostra(mostre);
		for(Studente s: this.studenti) {
			
			if(this.dao.opereViste(m)==null) {
				
			}else {
				
				List<Integer> opere = new ArrayList<>(this.dao.opereViste(m));
				
				for(Integer i: opere) {
					s.AggiungiOpera(i);
				}
				
			}
			
			Event ev = new Event(EventType.PROSSIMA_MOSTRA,m,s,m.getInizio());
			this.queue.add(ev);
		}
		
		while(!this.queue.isEmpty()) {
		    Event e = this.queue.poll();
		    processEvent(e);
		} 
		
	}
	
	
private void processEvent(Event e) {
		switch(e.getType()) {
		case PROSSIMA_MOSTRA:
			
			Mostra mos = e.getMostra();
			
			List<Mostra> vicini = Graphs.successorListOf(this.grafo, mos);
			
			if(vicini.size()>0) {
				
				Mostra nuova = this.estraiMostra(vicini);
				
				if(this.dao.opereViste(nuova)==null) {
					
				}else {
					
					List<Integer> opere = new ArrayList<>(this.dao.opereViste(nuova));
					
					for(Integer i: opere) {
						e.getStudente().AggiungiOpera(i);
					}
					
				}
				
								
				Event ev = new Event(EventType.PROSSIMA_MOSTRA,nuova,e.getStudente(),nuova.getInizio());
				this.queue.add(ev);
				
			}
			
			break;
		}
		
	}


public Mostra estraiMostra(List<Mostra> mostre) {
		
		int i = (int) (Math.random()*mostre.size());
		
		return mostre.get(i);
		
	}


public List<Studente> getStudenti() {
	Collections.sort(this.studenti);
	return studenti;
}



}

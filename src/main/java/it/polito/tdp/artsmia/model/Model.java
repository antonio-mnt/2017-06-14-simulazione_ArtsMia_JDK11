package it.polito.tdp.artsmia.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import it.polito.tdp.artsmia.db.ArtsmiaDAO;

public class Model {
	
	private ArtsmiaDAO dao;
	private SimpleDirectedGraph<Mostra, DefaultEdge> grafo;
	private List<Integer> anno;
	private List<Mostra> vertici;
	private Map<Integer,Mostra> idMap;
	private List<Arco> archi;
	private Map<Integer,Integer> esposizioni;
	
	private Simulator sim;
	
	
	public Model(){
		this.dao = new ArtsmiaDAO();
		this.anno = new ArrayList<>(this.dao.listAnni());
	}
	
	public void creaGrafo(int anno) {
		
		this.grafo = new SimpleDirectedGraph(DefaultEdge.class);
		this.vertici = new ArrayList<>(this.dao.listMostre(anno));
		Graphs.addAllVertices(this.grafo, this.vertici);
		
		this.idMap = new HashMap<>();
		
		for(Mostra m: this.vertici) {
			this.idMap.put(m.getId(), m);
		}
		
		this.archi = new ArrayList<>(this.dao.listArchi(anno, idMap));
		
		for(Arco a: this.archi) {
			this.grafo.addEdge(a.getM1(), a.getM2());
		}
		
		
	}

	public List<Integer> getAnno() {
		return anno;
	}
	
	public int getNumeroVertici() {
		return this.grafo.vertexSet().size();
	}
		
		
	public int getNumeroArchi() {
		return this.grafo.edgeSet().size();
	}
	
	public boolean verificaConnessione() {
		
		ConnectivityInspector<Mostra,DefaultEdge> graf = new ConnectivityInspector<>(this.grafo);
		
		int dimensione = graf.connectedSets().size();
		
		if(dimensione==1) {
			return true;
		}else {
			return false;
		}
		
		
		
	}
	
	public Mostra getMostraMigliore() {
		this.esposizioni = new HashMap<>();
		
		this.dao.riempiEsposizioni(esposizioni);
		
		Mostra mostra = null;
		int n = 0;
		
		for(Mostra mo: this.vertici) {
			Integer in = this.esposizioni.get(mo.getId());
			
			if(in!=null && in>n) {
				n = this.esposizioni.get(mo.getId());
				mostra = mo;
			}
		}
		
		return mostra;
	}
	
	
	public List<Studente> simula(int k, int anno){
		sim = new Simulator();
		
		List<Mostra> mostre = new ArrayList<>();
		
		for(Mostra m: this.vertici) {
			if(m.getInizio()==anno) {
				mostre.add(m);
			}
		}
		
		this.sim.simula(k, grafo, mostre);
		
		return this.sim.getStudenti();
	}

}

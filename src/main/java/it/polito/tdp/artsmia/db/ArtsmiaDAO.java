package it.polito.tdp.artsmia.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.artsmia.model.Arco;
import it.polito.tdp.artsmia.model.ArtObject;
import it.polito.tdp.artsmia.model.Mostra;

public class ArtsmiaDAO {

	public List<ArtObject> listObject() {
		
		String sql = "SELECT * from objects";

		List<ArtObject> result = new ArrayList<>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				
				result.add(new ArtObject(res.getInt("object_id"), res.getString("classification"), res.getString("continent"), 
						res.getString("country"), res.getInt("curator_approved"), res.getString("dated"), res.getString("department"), 
						res.getString("medium"), res.getString("nationality"), res.getString("object_name"), res.getInt("restricted"), 
						res.getString("rights_type"), res.getString("role"), res.getString("room"), res.getString("style"), res.getString("title")));
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	public List<Integer> listAnni() {
		
		String sql = "SELECT DISTINCT `begin` AS anno " + 
				"FROM exhibitions " + 
				"ORDER BY `begin` ";

		List<Integer> result = new ArrayList<>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(res.getInt("anno"));
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Mostra> listMostre(int anno) {
		
		String sql = "SELECT exhibition_id, exhibition_department, exhibition_title, `begin`, `end` " + 
				"FROM exhibitions " + 
				"WHERE `begin` >= ? ";

		List<Mostra> result = new ArrayList<>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				
				result.add(new Mostra(res.getInt("exhibition_id"), res.getString("exhibition_department"), res.getString("exhibition_title"), res.getInt("begin"), res.getInt("end")));
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	public List<Arco> listArchi(int anno, Map<Integer,Mostra> idMap) {
		
		String sql = "SELECT e1.exhibition_id, e2.exhibition_id, e1.`begin`, e1.`end` , e2.`begin`, e2.`end` " + 
				"FROM exhibitions AS e1, exhibitions AS e2 " + 
				"WHERE e1.exhibition_id <> e2.exhibition_id AND e2.`begin` > e1.`begin` " + 
				"AND e2.`begin` <= e1.`end` AND e1.`begin` >= ? ";

		List<Arco> result = new ArrayList<>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				
				Mostra m1 = idMap.get(res.getInt("e1.exhibition_id"));
				Mostra m2 = idMap.get(res.getInt("e2.exhibition_id"));
				
				if(m1!=null && m2!=null) {
					result.add(new Arco(m1,m2));
				}
				
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public void riempiEsposizioni(Map<Integer,Integer> esposizioni) {
		
		String sql = "SELECT exhibition_id, COUNT(object_id) AS n " + 
				"FROM exhibition_objects " + 
				"GROUP BY exhibition_id ";


		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			while (res.next()) {
			
				esposizioni.put(res.getInt("exhibition_id"), res.getInt("n"));
			}

			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public List<Integer> opereViste(Mostra m) {
		
		String sql = "SELECT distinct object_id " + 
				"FROM exhibition_objects " + 
				"WHERE exhibition_id = ? ";
		
		List<Integer> result = new ArrayList<>();


		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, m.getId());

			ResultSet res = st.executeQuery();

			while (res.next()) {
			
				result.add(res.getInt("object_id"));
			}

			conn.close();
			
			if(result.size() == 0) {
				return null;
			}else {
				return result;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}


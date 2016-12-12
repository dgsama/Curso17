package uo.ri.business.impl.cash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepairsByClientFinder {

	private String nombre;
	
	public RepairsByClientFinder(String nombre) {
		this.nombre = nombre;
	}

	public List<Map<String,Object>> execute(){
		List<Map<String,Object>> reparaciones = new ArrayList<Map<String,Object>>();
		Map<String,Object> reparación = new HashMap<String,Object>();
		
		return reparaciones;
	}
}

package uo.ri.business.impl;

import java.util.List;
import java.util.Map;

import uo.ri.business.AdminService;
import uo.ri.business.impl.admin.AddMechanic;

public class AdminServiceImpl implements AdminService {

	@Override
	public void newMechanic(String nombre, String apellidos) {
		new AddMechanic(info)
		
	}

	@Override
	public void deleteMechanic(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateMechanic(Long id, String nombre, String apellidos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Map<String, Object>> findAllMechanics() {
		// TODO Auto-generated method stub
		return null;
	}

}

package uo.ri.ui.admin.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import uo.ri.business.impl.AdminServiceImpl;
import uo.ri.common.BusinessException;
import uo.ri.conf.ServiceFactory;
import alb.util.console.Console;
import alb.util.jdbc.Jdbc;
import alb.util.menu.Action;

public class UpdateMechanicAction implements Action {


	@Override
	public void execute() throws BusinessException {
		
		// Pedir datos
		Long id = Console.readLong("Id del mecánico"); 
		String nombre = Console.readString("Nombre"); 
		String apellidos = Console.readString("Apellidos");
		
		ServiceFactory sf = new ServiceFactory();
		sf.getAdminService().updateMechanic(id, nombre, apellidos);
		
		
		// Mostrar resultado
		Console.println("Mecánico actualizado");
	}

}

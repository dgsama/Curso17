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

public class DeleteMechanicAction implements Action {

	

	@Override
	public void execute() throws BusinessException {
		
		Long idMecanico = Console.readLong("Id de mecánico"); 
		

		ServiceFactory sf = new ServiceFactory();
		sf.getAdminService().deleteMechanic(idMecanico);
		
		
		Console.println("Se ha eliminado el mecánico");
	}

}

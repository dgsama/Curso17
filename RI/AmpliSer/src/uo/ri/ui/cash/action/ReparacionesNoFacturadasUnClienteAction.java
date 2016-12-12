package uo.ri.ui.cash.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.common.BusinessException;
import uo.ri.conf.ServiceFactory;

public class ReparacionesNoFacturadasUnClienteAction implements Action {

	/**
	 * Proceso:
	 * 
	 *   - Pide el DNI del cliente
	 *    
	 *   - Muestra en pantalla todas sus averias no facturadas 
	 *   		(status <> 'FACTURADA'). De cada avería muestra su 
	 *   		id, fecha, status, importe y descripción
	 */
	@Override
	public void execute() throws BusinessException {
		String s = Console.readString("String");
		ServiceFactory sf = new ServiceFactory();
		sf.getCashService().findRepairsByClient(s);
		
		
		/*
		Connection con;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuilder sb = new StringBuilder();
		Scanner input = new Scanner(System.in);
		int dni = input.nextInt();
		sb.append("SELECT ID, FECHA, STATUS, IMPORTE, DESCRIPCION FROM TAVERIAS, TVEHICULOS, TCLIENTES"
				+ "WHERE STATUS <> 'FACTURADA'"
				+ "AND TAVERIAS.VEHICULO_ID = TVEHICULOS.ID"
				+ "AND TVEHICULOS.CLIENTE_ID = TCLIENTES.ID"
				+ "AND TCLIENTES.DNI = ?");
		try{
		
		ps = con.prepareStatement(sb.toString());
		ps.setInt(1, dni);
		rs = ps.executeQuery();
		while(rs.next()){
			
		}}catch(Exception e){}
		finally{
		if(rs != null) try{ rs.close();}catch(SQLException e){}
		if(ps != null) try{ ps.close();}catch(SQLException e){}
		if(con != null) try{ con.close();}catch(SQLException e){}
		}
	
	*/
	}

}

package uo.ri.ui.admin.action;


import java.util.Map;

import uo.ri.business.conf.ServicesFactory;
import uo.ri.common.BusinessException;
import alb.util.console.Console;
import alb.util.menu.Action;

public class ListMechanicsAction implements Action {

	
	@Override
	public void execute() throws BusinessException {

		Console.println("\nListado de mecánicos\n");  

		//v2//new AdminServiceImpl().findAllMechanics();
		
		for (Map<String, Object> mec : ServicesFactory.getAdminService().findAllMechanics()){
			Console.print( String.valueOf(mec.get("id")) );
			Console.print( "\t" + (String)mec.get("nombre") );
			Console.print( "\t" + (String)mec.get("apellidos") + "\n" );
		}
	}
}

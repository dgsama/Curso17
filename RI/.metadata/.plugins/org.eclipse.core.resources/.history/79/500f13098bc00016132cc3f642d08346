package uo.ri.ui.admin.action;

import java.util.HashMap;
import java.util.Map;

import uo.ri.business.AdminService;
import uo.ri.business.impl.AdminServiceImpl;
import uo.ri.business.impl.admin.DeleteMechanic;
import uo.ri.common.BusinessException;
import alb.util.console.Console;
import alb.util.menu.Action;

public class DeleteMechanicAction implements Action {

	@Override
	public void execute() throws BusinessException {
		Long idMecanico = Console.readLong("Id de mecánico");

		AdminService admin = new AdminServiceImpl();
		admin.deleteMechanic(idMecanico);
	}

}

package uo.ri.ui.foreman.action;

import uo.ri.business.impl.foreman.RegistrarAveria;
import uo.ri.common.BusinessException;
import alb.util.menu.Action;

public class RegistrarAveriaAction implements Action {

	@Override
	public void execute() throws BusinessException {
		new RegistrarAveria().execute();
		
	}

}

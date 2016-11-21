package uo.ri.ui.foreman.action;

import uo.ri.business.impl.foreman.ModificarAveria;
import uo.ri.common.BusinessException;
import alb.util.menu.Action;

public class ModificarAveriaAction implements Action {

	@Override
	public void execute() throws BusinessException {
		new ModificarAveria().execute();

	}

}

package org.openpos.utils;

import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.DataLogicSystem;

public abstract class ResourcesHandler {

	protected DataLogicSystem dataLogicSystem;

	protected ResourcesHandler(AppView app) {
		dataLogicSystem = (DataLogicSystem)app.getBean("com.openbravo.pos.forms.DataLogicSystem");
	}
}

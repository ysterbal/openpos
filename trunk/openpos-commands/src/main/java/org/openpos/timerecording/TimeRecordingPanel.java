package org.openpos.timerecording;

import javax.swing.JComponent;

import com.openbravo.basic.BasicException;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;

public class TimeRecordingPanel implements JPanelView, BeanFactoryApp {

	private TimeRecordingView timeRecordingView = new TimeRecordingView();

	@Override
	public Object getBean() {
		return this;
	}

	@Override
	public void init(AppView app) throws BeanFactoryException {
		// TODO Auto-generated method stub

	}

	@Override
	public String getTitle() {
		return "Zeiterfassung";
	}

	@Override
	public void activate() throws BasicException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean deactivate() {
		return true;
	}

	@Override
	public JComponent getComponent() {
		return timeRecordingView;
	}
}

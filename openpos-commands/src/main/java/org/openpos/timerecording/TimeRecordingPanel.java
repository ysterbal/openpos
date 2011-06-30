package org.openpos.timerecording;

import java.awt.FlowLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;

import org.openpos.print.ReportPrintService;

import com.openbravo.basic.BasicException;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.IDataLogicSystem;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.printer.TicketParser;

public class TimeRecordingPanel implements JPanelView, BeanFactoryApp {

	private TimeRecordingView timeRecordingView = new TimeRecordingView();

	@Override
	public Object getBean() {
		return this;
	}

	@Override
	public void init(AppView app) throws BeanFactoryException {
		IDataLogicSystem dataLogicSystem = (IDataLogicSystem)app.getBean("com.openbravo.pos.forms.DataLogicSystem");
		TicketParser ticketParser = new TicketParser(app.getDeviceTicket(), dataLogicSystem);

		String employeeList = dataLogicSystem.getResourceAsText("Employee.List");
		if (employeeList != null) {
			EmployeeDataBase employeeDataBase = EmployeeDataBase.getInstance(employeeList);
			timeRecordingView.setEmployees(employeeDataBase.getEmployees());
		}
		else
			throw new BeanFactoryException("Resource not defined");

		String employeeDailyReport = dataLogicSystem.getResourceAsText("Employee.Daily.Report");
		if (employeeDailyReport != null && !employeeDailyReport.isEmpty()) {
			ReportPrintService reportPrintService = new ReportPrintService(ticketParser, timeRecordingView,
					employeeDailyReport);
			timeRecordingView.setReportPrintService(reportPrintService);
		}
		else
			throw new BeanFactoryException("Resource not defined");

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
		JPanel panel = new JPanel(new FlowLayout());
		panel.add(timeRecordingView);
		return panel;
	}
}

package org.openpos.timerecording;

import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.IDataLogicSystem;
import com.openbravo.pos.printer.TicketParser;

public class TimeRecordingResources {

	private TicketParser ticketParser;
	private String employeeDailyReport;
	private String employeeMonthlyReport;
	private EmployeeDataBase employeeDataBase;

	public TimeRecordingResources(AppView app) {
		IDataLogicSystem dataLogicSystem = (IDataLogicSystem)app.getBean("com.openbravo.pos.forms.DataLogicSystem");
		ticketParser = new TicketParser(app.getDeviceTicket(), dataLogicSystem);

		String employeeList = dataLogicSystem.getResourceAsText("Employee.List");
		if (employeeList != null) {
			employeeDataBase = EmployeeDataBase.getInstance(employeeList);
		}
		else
			throw new BeanFactoryException("Resource not defined");

		employeeDailyReport = dataLogicSystem.getResourceAsText("Employee.Daily.Report");
		if (employeeDailyReport == null || employeeDailyReport.isEmpty()) {
			throw new BeanFactoryException("Resource not defined");
		}
		employeeMonthlyReport = dataLogicSystem.getResourceAsText("Employee.Monthly.Report");
		if (employeeMonthlyReport == null || employeeMonthlyReport.isEmpty()) {
			throw new BeanFactoryException("Resource not defined");
		}
	}

	public TicketParser getTicketParser() {
		return ticketParser;
	}

	public String getEmployeeDailyReport() {
		return employeeDailyReport;
	}

	public String getEmployeeMonthlyReport() {
		return employeeMonthlyReport;
	}

	public EmployeeDataBase getEmployeeDataBase() {
		return employeeDataBase;
	}

}

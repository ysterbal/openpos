package org.openpos.timerecording;

import java.util.ArrayList;
import java.util.List;

import com.openbravo.pos.scripting.ScriptEngine;
import com.openbravo.pos.scripting.ScriptException;
import com.openbravo.pos.scripting.ScriptFactory;

public class EmployeeDataBase {

	private List<Employee> employees = new ArrayList<Employee>();

	public List<Employee> getEmployees() {
		return employees;
	}

	public void addEmployee(String name, double wageRate) {
		employees.add(new Employee(name, wageRate));
	}

	public static EmployeeDataBase getInstance(String script) {
		try {
			EmployeeDataBase employeeDataBase = new EmployeeDataBase();
			ScriptEngine eng = ScriptFactory.getScriptEngine(ScriptFactory.BEANSHELL);
			eng.put("edb", employeeDataBase);
			eng.eval(script);
			return employeeDataBase;
		}
		catch (ScriptException e) {
			throw new RuntimeException(e);
		}
	}
}

package org.openpos.timerecording;

public class Employee {

	private String name;
	private double wageRate;

	public Employee(String name, double wageRate) {
		this.name = name;
		this.wageRate = wageRate;
	}

	public String getName() {
		return name;
	}

	public double getWageRate() {
		return wageRate;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setWageRate(double wageRate) {
		this.wageRate = wageRate;
	}

}

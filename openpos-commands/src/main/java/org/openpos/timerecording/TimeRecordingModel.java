package org.openpos.timerecording;

import java.util.Calendar;

public class TimeRecordingModel {

	private Employee employee;
	private int commingTime;
	private int leavingTime;
	private int pauseDuration;
	private Calendar date = Calendar.getInstance();

	private int workingTime;

	public boolean isValid() {
		return employee != null && workingTime != 0;
	}

	public int getCommingTime() {
		return commingTime;
	}

	public int getLeavingTime() {
		return leavingTime;
	}

	public int getPauseDuration() {
		return pauseDuration;
	}

	public Calendar getDate() {
		return date;
	}

	public int getWorkingTime() {
		return workingTime;
	}

	public void setCommingTime(int commingTime) {
		this.commingTime = commingTime;
	}

	public void setLeavingTime(int leavingTime) {
		this.leavingTime = leavingTime;
	}

	public void setPauseDuration(int pauseDuration) {
		this.pauseDuration = pauseDuration;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public void setWorkingTime(int workingTime) {
		this.workingTime = workingTime;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}

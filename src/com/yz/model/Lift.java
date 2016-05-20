package com.yz.model;

public class Lift {
	private Integer id;
	private Integer projectId;
	private Integer DTUnumber;
	private String attendanceData;
	private Boolean hoistingAlarm;
	private Boolean squattingAlarm;

	public Lift(){
		
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getDTUnumber() {
		return DTUnumber;
	}

	public void setDTUnumber(Integer unumber) {
		DTUnumber = unumber;
	}

	public String getAttendanceData() {
		return attendanceData;
	}

	public void setAttendanceData(String attendanceData) {
		this.attendanceData = attendanceData;
	}

	public Boolean getHoistingAlarm() {
		return hoistingAlarm;
	}

	public void setHoistingAlarm(Boolean hoistingAlarm) {
		this.hoistingAlarm = hoistingAlarm;
	}

	public Boolean getSquattingAlarm() {
		return squattingAlarm;
	}

	public void setSquattingAlarm(Boolean squattingAlarm) {
		this.squattingAlarm = squattingAlarm;
	}

}

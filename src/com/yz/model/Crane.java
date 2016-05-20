package com.yz.model;

public class Crane {
	private Integer id;
	private Integer projectId;
	private Integer DTUnumber;
	private String attendanceData;

	private Boolean weightWarning;
	private Boolean heightWarning;
	private Boolean widthWarning;
	private Boolean angleWarning;
	private Boolean windWarning;
	private Boolean inclinationWarning;
	private Boolean relaysWarning;

	private Boolean weightAlarm;
	private Boolean torqueAlarm;
	private Boolean heightAlarm;
	private Boolean widthAlarm;
	private Boolean angleAlarm;
	private Boolean windAlarm;
	private Boolean inclinationAlarm;

	public Crane(){
		
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

	public Boolean getWeightWarning() {
		return weightWarning;
	}

	public void setWeightWarning(Boolean weightWarning) {
		this.weightWarning = weightWarning;
	}

	public Boolean getHeightWarning() {
		return heightWarning;
	}

	public void setHeightWarning(Boolean heightWarning) {
		this.heightWarning = heightWarning;
	}

	public Boolean getWidthWarning() {
		return widthWarning;
	}

	public void setWidthWarning(Boolean widthWarning) {
		this.widthWarning = widthWarning;
	}

	public Boolean getAngleWarning() {
		return angleWarning;
	}

	public void setAngleWarning(Boolean angleWarning) {
		this.angleWarning = angleWarning;
	}

	public Boolean getWindWarning() {
		return windWarning;
	}

	public void setWindWarning(Boolean windWarning) {
		this.windWarning = windWarning;
	}

	public Boolean getInclinationWarning() {
		return inclinationWarning;
	}

	public void setInclinationWarning(Boolean inclinationWarning) {
		this.inclinationWarning = inclinationWarning;
	}

	public Boolean getRelaysWarning() {
		return relaysWarning;
	}

	public void setRelaysWarning(Boolean relaysWarning) {
		this.relaysWarning = relaysWarning;
	}

	public Boolean getWeightAlarm() {
		return weightAlarm;
	}

	public void setWeightAlarm(Boolean weightAlarm) {
		this.weightAlarm = weightAlarm;
	}

	public Boolean getTorqueAlarm() {
		return torqueAlarm;
	}

	public void setTorqueAlarm(Boolean torqueAlarm) {
		this.torqueAlarm = torqueAlarm;
	}

	public Boolean getHeightAlarm() {
		return heightAlarm;
	}

	public void setHeightAlarm(Boolean heightAlarm) {
		this.heightAlarm = heightAlarm;
	}

	public Boolean getWidthAlarm() {
		return widthAlarm;
	}

	public void setWidthAlarm(Boolean widthAlarm) {
		this.widthAlarm = widthAlarm;
	}

	public Boolean getAngleAlarm() {
		return angleAlarm;
	}

	public void setAngleAlarm(Boolean angleAlarm) {
		this.angleAlarm = angleAlarm;
	}

	public Boolean getWindAlarm() {
		return windAlarm;
	}

	public void setWindAlarm(Boolean windAlarm) {
		this.windAlarm = windAlarm;
	}

	public Boolean getInclinationAlarm() {
		return inclinationAlarm;
	}

	public void setInclinationAlarm(Boolean inclinationAlarm) {
		this.inclinationAlarm = inclinationAlarm;
	}

}

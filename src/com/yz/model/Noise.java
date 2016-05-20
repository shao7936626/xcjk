package com.yz.model;

public class Noise {
	private Integer id;
	private Integer projectId;
	private Integer DTUnumber;
	private String data;
	
	public Noise(){
		
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
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	
}

package com.example.headhunter.entities;

import java.util.ArrayList;
import java.util.List;

public class Item {
	private Boolean premium;
	private String published_at;
	private Object address;
	private String alternate_url;
	private String id;
	private Salary salary;
	private Boolean archived;
	private String name;
	private Area area;
	private String url;
	private String created_at;
	private List<Object> relations = new ArrayList<Object>();
	private Employer employer;
	private Boolean response_letter_required;
	private Type type;

	public Boolean getPremium() {
		return premium;
	}

	public void setPremium(Boolean premium) {
		this.premium = premium;
	}

	public String getPublished_at() {
		return published_at;
	}

	public void setPublished_at(String published_at) {
		this.published_at = published_at;
	}

	public Object getAddress() {
		return address;
	}

	public void setAddress(Object address) {
		this.address = address;
	}

	public String getAlternate_url() {
		return alternate_url;
	}

	public void setAlternate_url(String alternate_url) {
		this.alternate_url = alternate_url;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Salary getSalary() {
		return salary;
	}

	public void setSalary(Salary salary) {
		this.salary = salary;
	}

	public Boolean getArchived() {
		return archived;
	}

	public void setArchived(Boolean archived) {
		this.archived = archived;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public List<Object> getRelations() {
		return relations;
	}

	public void setRelations(List<Object> relations) {
		this.relations = relations;
	}

	public Employer getEmployer() {
		return employer;
	}

	public void setEmployer(Employer employer) {
		this.employer = employer;
	}

	public Boolean getResponse_letter_required() {
		return response_letter_required;
	}

	public void setResponse_letter_required(Boolean response_letter_required) {
		this.response_letter_required = response_letter_required;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
}

package com.example.demo.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message = "Label field can't be left empty")
	@Column(unique = true)
	private String label;
	
	@NotEmpty(message = "Color field can't be left empty")
	@Column(unique = true)
	private String color;

	@ManyToMany(mappedBy = "categories")
	@JsonIgnore
	private List<Pic> pics;
	
	public Category() {}
	public Category(String label, String color) {
		setLabel(label);
		setColor(color);
	}	
	public Category(String label, String color, List<Pic> pics) {
		setLabel(label);
		setColor(color);
		setPics(pics);
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}

	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	public List<Pic> getPics() {
		return pics;
	}
	public void setPics(List<Pic> pics) {
		this.pics = pics;
	}
	@Override
	public String toString() {
		return getLabel();
	}
	
}

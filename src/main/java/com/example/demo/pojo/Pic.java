package com.example.demo.pojo;


import java.util.List;

import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table
public class Pic {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message = "Title field can't be left empty")
	@Column(unique = true)
	private String title;
	
	@Column
	private String description;
	
	@NotEmpty(message = "Url field can't be left empty")
	@URL(message = "Url is not valid")
	private String url;
	
	@Column
	private String tag;
	
	@NotNull(message = "Visibility value can't be null")
	private boolean visible;
	
	
	@ManyToMany
	@JsonIgnore
	private List<Category> categories;
	
	@OneToMany(mappedBy = "pic", cascade = CascadeType.REMOVE)
	private List<Comment> comments;
	
	public Pic() {}
	public Pic(String title, String description, String url, String tag, boolean visible, List<Category> categories) {
		setTitle(title);
		setDescription(description);
		setUrl(url);
		setTag(tag);
		setVisible(visible);	
		setCategories(categories);
	}	
	public Pic(String title, String description, String url, String tag, boolean visible, Category category) {
		setTitle(title);
		setDescription(description);
		setUrl(url);
		setTag(tag);
		setVisible(visible);
		addCategory(category);
	}

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
		
	public List<Category> getCategories() {
		return categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	
	public void addCategory(Category category) {
		
		boolean finded = false;
		
		for (Category cat : getCategories())
			if (cat.getId() == category.getId())
				finded = true;
		if(!finded)
			getCategories().add(category);
	}

		
	@Override
	public String toString() {
		return getTitle()
				+ " (id: " + getId() + ")";
	}
}

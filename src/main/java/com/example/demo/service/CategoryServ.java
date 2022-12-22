package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.pojo.Category;
import com.example.demo.repository.CategoryRepo;

@Service
public class CategoryServ {
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	public void save(Category category) {
		categoryRepo.save(category);
	}
	
	public void delete(int id) {
		categoryRepo.deleteById(id);
	}
	
	public List<Category> findAll(){
		return categoryRepo.findAll();
	}
	
	public Optional<Category> getPicById(int id){
		return categoryRepo.findById(id);
	}


}

package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.Category;
import com.example.demo.service.PicServ;

@RestController
@RequestMapping("/api/1/categories")
@CrossOrigin("*")
public class ApiCategoryController {
	
	@Autowired
	PicServ picService;
	
	@GetMapping("/pic/{id}")
	public List<Category> getCategoriesByPhotoId(@PathVariable("id") int id) {
		return picService.getPicById(id).get().getCategories();
	}

}

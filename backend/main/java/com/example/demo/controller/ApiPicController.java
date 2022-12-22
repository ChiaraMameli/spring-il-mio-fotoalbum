package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.Pic;
import com.example.demo.service.PicServ;

@RestController
@RequestMapping("/api/1/pics")
@CrossOrigin("*")
public class ApiPicController {
	
	@Autowired
	PicServ picService;
	
	@GetMapping("")
	public List<Pic> getAll() {
		List<Pic> pics = picService.fetchByVisibility();
		return pics;
	}
	
	@GetMapping("/search/{query}")
	public List<Pic> searchPhotoByTitleOrTag(@PathVariable("query") String query) {
		List<Pic> pics = query == null ? picService.fetchByVisibility() : picService.findByNameOrTag(query);
		return pics;
 	}

}

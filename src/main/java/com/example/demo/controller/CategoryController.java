package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.pojo.Category;
import com.example.demo.service.CategoryServ;

import jakarta.validation.Valid;

@Controller
@RequestMapping("user/category")
public class CategoryController {
	
	@Autowired
	CategoryServ categoryService;
	
	@GetMapping("")
	public String index(Model model) {
		
		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);
		
		return "category-index";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable("id") int id, Model model) {
		
		Optional<Category> optCat = categoryService.getPicById(id);
		Category category = optCat.get();
		model.addAttribute("category", category);
		
		return "category-show";		
	}
	
	@GetMapping("/create")
	public String createCategory(Model model) {
		
		Category category = new Category();
		model.addAttribute("category", category);
		
		return "category-create";
	}
	
	@PostMapping("/create")
	public String storeCategory(@Valid @ModelAttribute("category") Category category, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
		
		redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
		
		return "redirect:/user/category/create";
		}
	
		categoryService.save(category);
		
		return "redirect:/user/category";
	}

	@GetMapping("/update/{id}")
	public String editCategory(@PathVariable("id") int id, Model model) {
		
		Optional<Category> optCat = categoryService.getPicById(id);
		
		Category category = optCat.get();		
		model.addAttribute("category", category);
		
		return "category-update";
	}
	
	@PostMapping("/update")
	public String updateCategory(@Valid Category category, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
			if (bindingResult.hasErrors()) {
			
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			
			return "redirect:/user/category";
			}
			
			categoryService.save(category);
		
		return "redirect:/user/category";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteCategory(@PathVariable("id") int id) {
		
		categoryService.delete(id);
		
		return "redirect:/user/category";
	}


}

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
import com.example.demo.pojo.Pic;
import com.example.demo.service.CategoryServ;
import com.example.demo.service.PicServ;

import jakarta.validation.Valid;

@Controller
@RequestMapping("user/pic")
public class PicController {

	@Autowired
	PicServ picService;
	
	@Autowired
	CategoryServ categoryService;
	
	@GetMapping("")
	public String index(Model model) {
		
		List<Pic> pics = picService.findAll();
		model.addAttribute("pics", pics);

		return "pic-index";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable("id") int id, Model model) {
		
		Optional<Pic> optPic = picService.getPicById(id);
		Pic pic = optPic.get();
		model.addAttribute("pic", pic);
		
		return "pic-show";		
	}
	
	@GetMapping("/create")
	public String createPic(Model model) {
		
		Pic pic = new Pic();
		model.addAttribute("pic", pic);
		
		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);
		
		return "pic-create";
	}
	
	@PostMapping("/create")
	public String storePic(@Valid @ModelAttribute("pic") Pic pic, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
		
		redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
		
		return "redirect:/user/pic/create";
		}
	
		picService.save(pic);
		
		return "redirect:/user/pic";
	}
	
	@GetMapping("/update/{id}")
	public String editPic(@PathVariable("id") int id, Model model) {
		
		Optional<Pic> optPic = picService.getPicById(id);
		
		Pic pic = optPic.get();		
		model.addAttribute("pic", pic);
		
		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);
		
		return "pic-update";
	}
	
	@PostMapping("/update")
	public String updatePic(@Valid Pic pic, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
			if (bindingResult.hasErrors()) {
			
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			
			return "redirect:/";
			}
			
			picService.save(pic);
		
		return "redirect:/user/pic";
	}

	@GetMapping("/delete/{id}")
	public String deletePic(@PathVariable("id") int id) {
		
		picService.delete(id);
		
		return "redirect:/user/pic";
	}

}

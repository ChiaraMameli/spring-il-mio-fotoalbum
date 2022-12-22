package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.pojo.Pic;
import com.example.demo.repository.PicRepo;

import jakarta.transaction.Transactional;

@Service
public class PicServ {

	@Autowired
	private PicRepo picRepo;
	
	public void save(Pic pic) {
		picRepo.save(pic);
	}
	
	public void delete(int id) {
		picRepo.deleteById(id);
	}

	public List<Pic> findAll(){
		return picRepo.findAll();
	}
	
	public Optional<Pic> getPicById(int id){
		return picRepo.findById(id);
	}

	public List<Pic> findByNameOrTag(String query) {
		return picRepo.findByTitleContainingOrTagContaining(query, query);
	}
	public List<Pic> fetchByVisibility() {
		return picRepo.findByVisibleTrue();
	}

	
	@Transactional
	public List<Pic> findAllWCategoryAndComment() {
		List<Pic> pics = picRepo.findAll();
		for (Pic pic : pics) {
			Hibernate.initialize(pic.getCategories());
			Hibernate.initialize(pic.getComments());
		}
		
		return pics;
	}


}

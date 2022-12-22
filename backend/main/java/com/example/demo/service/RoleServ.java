package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.pojo.Role;
import com.example.demo.repository.RoleRepo;

@Service
public class RoleServ {

	@Autowired
	private RoleRepo roleRepo;

	
	public void save(Role role) {			
			roleRepo.save(role);
		}
	
	public void delete(Role role) {		
		roleRepo.delete(role);
	}
	
	public Role getById(int id) {		
		return roleRepo.findById(id).get();
	}
	
	public List<Role> findAll() {		
		return roleRepo.findAll();
	}

}

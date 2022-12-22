package com.example.demo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.pojo.Category;
import com.example.demo.pojo.Comment;
import com.example.demo.pojo.Pic;
import com.example.demo.pojo.Role;
import com.example.demo.pojo.User;
import com.example.demo.service.CategoryServ;
import com.example.demo.service.CommentServ;
import com.example.demo.service.PicServ;
import com.example.demo.service.RoleServ;
import com.example.demo.service.UserServ;
import com.github.javafaker.Faker;


@SpringBootApplication
public class SpringIlMioFotoalbumApplication implements CommandLineRunner{

	@Autowired
	private PicServ picServ;
	
	@Autowired
	private CategoryServ categoryServ;
	
	@Autowired
	private RoleServ roleServ;
	
	@Autowired
	private UserServ userServ;
	
	@Autowired
	private CommentServ commentServ;


	public static void main(String[] args) {
		SpringApplication.run(SpringIlMioFotoalbumApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Faker faker = new Faker();
		
		String pippoPic = "https://www.tomshw.it/images/images/2021/08/pippo-180614.jpg";
		
		Category catLab1 = new Category("Panorama", "blue");
		Category catLab2 = new Category("Portrait", "yellow");
		Category catLab3 = new Category("cartoon", "red");
		
		List<Category> cat1 = new ArrayList<>();
		cat1.add(catLab3);
		
		List<Category> cat2 = new ArrayList<>();
		cat1.add(catLab2);
		
		categoryServ.save(catLab1);
		categoryServ.save(catLab2);
		categoryServ.save(catLab3);
		
		for(int x = 0; x < 20; x++) {
			
			Pic pic = new Pic(
						faker.name().firstName(), 
						faker.shakespeare().hamletQuote(),
						pippoPic,
						faker.name().title(), 
						faker.bool().bool(), 
						cat1
						);
			picServ.save(pic);
						
			
			Comment com = new Comment(faker.harryPotter().quote(), pic);
			commentServ.save(com);
		}

		
		Role userRole = new Role("USER");
		
		roleServ.save(userRole);
		
		User userUser = new User("user", "{noop}userpsw", userRole);
		
		Set<Role> userAdminRoles = new HashSet<>();
		userAdminRoles.add(userRole);
		
		userServ.save(userUser);

	}

	
}

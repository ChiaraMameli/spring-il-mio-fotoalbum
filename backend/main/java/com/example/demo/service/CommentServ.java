package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.pojo.Comment;
import com.example.demo.repository.CommentRepo;

@Service
public class CommentServ {
	@Autowired
	CommentRepo commentRepo;
	
	public Comment save(Comment comment) {
		return commentRepo.save(comment);
	}
	
	public List<Comment> findAll() {
		return commentRepo.findAll();
	}
	
	public Optional<Comment> findCommentById(int id) {
		return commentRepo.findById(id);
	}

}

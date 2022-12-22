package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.Comment;
import com.example.demo.pojo.Pic;
import com.example.demo.service.CommentServ;
import com.example.demo.service.PicServ;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/1/comments")
@CrossOrigin("*")
public class ApiCommentController {
	
	@Autowired
	private PicServ picService;
	
	@Autowired
	private CommentServ commentService;
	
	@GetMapping("/{id}")
	public List<Comment> getCommentsByPhotoId(@PathVariable("id") int id) {
		return picService.getPicById(id).get().getComments();
	}
	
	@PostMapping("/add/{id}")
	public Comment addCommentToPhoto(@PathVariable("id") int id, @Valid @RequestBody Comment comment) {		
		
		Pic currentPic = picService.getPicById(id).get();		
		Comment currentComment = new Comment(comment.getContent(), currentPic);		
		commentService.save(currentComment);	
		
		return currentComment;
	}
}

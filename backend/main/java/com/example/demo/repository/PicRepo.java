package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.pojo.Pic;

@Repository
public interface PicRepo extends JpaRepository<Pic, Integer> {
	public List<Pic> findByTitleContainingOrTagContaining(String title, String tag);
	public List<Pic> findByVisibleTrue();
}

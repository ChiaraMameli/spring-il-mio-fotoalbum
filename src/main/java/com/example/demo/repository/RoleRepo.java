package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.pojo.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {

}

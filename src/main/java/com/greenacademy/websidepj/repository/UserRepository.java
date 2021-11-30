package com.greenacademy.websidepj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.greenacademy.websidepj.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("SELECT distinct u FROM User u WHERE u.userName = :userName")
	public User findByUserName (@Param("userName") String userName); 
	
	@Query("select u from User u where u.firstName like %:name% or u.lastName like %:name%")
	public List<User> searchByName(@Param("name")String name);
}

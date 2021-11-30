package com.greenacademy.websidepj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.greenacademy.websidepj.entity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
	
	@Query("SELECT distinct p FROM Permission p WHERE p.permissionName = :name")
	public Permission getByName(@Param("name") String name);
	
	@Query("SELECT DISTINCT p FROM Permission p WHERE p.permissionName = :permissionName")
	public Permission findByPermissionName(@Param("permissionName") String permissionName);
}

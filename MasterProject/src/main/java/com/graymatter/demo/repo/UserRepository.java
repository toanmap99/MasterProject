/*
 * Toan Nguyen
 * Master Project
 * 02/24/2025
 */

package com.graymatter.demo.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.graymatter.demo.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	
	@Query("SELECT u FROM User u WHERE u.username = :username")
	public User getUserByUsername(@Param("username") String username); // only SQL statement due to the relationship between 3 tables.

}

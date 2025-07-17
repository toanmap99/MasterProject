/*
 * Toan Nguyen
 * Master Project
 * 02/24/2025
 */

package com.graymatter.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.graymatter.demo.model.Department;
//Repo Interface connection between model and service
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer>{

}

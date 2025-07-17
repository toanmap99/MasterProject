/*
 * Toan Nguyen
 * Master Project
 * 02/24/2025
 */

package com.graymatter.demo.service;

import java.io.FileNotFoundException;
import java.util.List;

import com.graymatter.demo.model.Department;

import net.sf.jasperreports.engine.JRException;

public interface DepartmentService {
	List<Department> getAllDepartments();
	void saveDepartment(Department department);
	Department getDepartmentById(int id);
	void deleteDepartmentById(int id);
	String exportDepartmentReport(String format) throws FileNotFoundException, JRException;
}

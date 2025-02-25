/*
 * Toan Nguyen
 * Master Project
 * 02/24/2025
 */

package com.graymatter.demo.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.graymatter.demo.model.Department;
import com.graymatter.demo.repo.DepartmentRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	private DepartmentRepository departmentRepository;

	// Get all department method
	@Override
	public List<Department> getAllDepartments() {
		return departmentRepository.findAll();
	}

	// save a department after update method
	@Override
	public void saveDepartment(Department department) {
		this.departmentRepository.save(department);
	}
		

	// get a department by ID method
	@Override
	public Department getDepartmentById(int id) {
		Optional<Department> optional = departmentRepository.findById(id);
		Department department = null;
		if(optional.isPresent()) {
			department = optional.get();
		}else {
			throw new RuntimeException("Department not found for id :: " + id);
		}
		return department;
	}

	// delete a department method
	@Override
	public void deleteDepartmentById(int id) {
		this.departmentRepository.deleteById(id);
		
	}

	@Override
	public String exportDepartmentReport(String format) throws FileNotFoundException, JRException {
		List<Department> departmentList = getAllDepartments();	
		String path = "D://JasperReports//"; //File location
		
		File file = ResourceUtils.getFile("classpath:Departments.jrxml"); // Get the pringting format
		JasperReport jasper = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(departmentList);		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("Department", "List");		
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasper, parameters, ds);
		
		if(format.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint, path +"//departments.html"); // File output in an HTML form
		}
		if(format.equalsIgnoreCase("pdf")) {
			JasperExportManager.exportReportToPdfFile(jasperPrint, path +"//departments.pdf"); // File output in a PDF form
		}
		
		return "path : "+path;
	}

}

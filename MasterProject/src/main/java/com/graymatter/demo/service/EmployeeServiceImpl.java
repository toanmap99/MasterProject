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

import com.graymatter.demo.model.Employee;
import com.graymatter.demo.repo.EmployeeRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	// Get all employee method
	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	// Save employee after update method
	@Override
	public void saveEmployee(Employee employee) {
		this.employeeRepository.save(employee);
	}

	// Get employee by ID
	@Override
	public Employee getEmployeeById(int id) {
		Optional<Employee> optional = employeeRepository.findById(id);
		Employee employee = null;
		if(optional.isPresent()) {
			employee = optional.get();
		}else {
			throw new RuntimeException("Employee not found for id :: " + id);
		}
		return employee;
	}

	// Delete an employee method
	@Override
	public void deleteEmployeeById(int id) {
		this.employeeRepository.deleteById(id);
		
	}
	
	// Printing Report method
	public String exportReport(String format) throws FileNotFoundException, JRException {
		List<Employee> employeeList = getAllEmployees();	
		String path = "D://JasperReports//"; //File location
		
		File file = ResourceUtils.getFile("classpath:Employees.jrxml"); // Get the pringting format
		JasperReport jasper = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(employeeList);		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("Employee", "List");		
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasper, parameters, ds);
		
		if(format.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint, path +"//employees.html"); // File output in an HTML form
		}
		if(format.equalsIgnoreCase("pdf")) {
			JasperExportManager.exportReportToPdfFile(jasperPrint, path +"//employees.pdf"); // File output in a PDF form
		}
		
		return "path : "+path;
	}

}

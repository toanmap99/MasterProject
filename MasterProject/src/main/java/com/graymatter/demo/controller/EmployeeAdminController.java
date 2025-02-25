/*
 * Toan Nguyen
 * Master Project
 * 02/24/2025
 */

package com.graymatter.demo.controller;


import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.graymatter.demo.service.BranchService;
import com.graymatter.demo.service.DepartmentService;
import com.graymatter.demo.service.EmployeeService;

import net.sf.jasperreports.engine.JRException;

@Controller
public class EmployeeAdminController {
	
	@Autowired
	private EmployeeService employeeService;
	
	// Link to respective pages
	
	@GetMapping("/admin/employee-datatable")
	public String employeeDataTable(Model model) {
		model.addAttribute("listEmployees",employeeService.getAllEmployees());
		return "employee/employee_table_list";
	}
	
	
	@GetMapping("/admin/employee-datatable-users")
	public String employeeDataTableUsers(Model model) {
		model.addAttribute("listEmployees",employeeService.getAllEmployees());
		return "employee/employee_table_list_users";
	}
	
	
	@GetMapping("/admin/employee-datatable/export/{format}")
	public String exportReport(@PathVariable String format,Model model) throws FileNotFoundException, JRException {
		model.addAttribute("listEmployees",employeeService.exportReport(format));
		return "redirect:/admin/employee-datatable";
	}
	
	
	@Autowired
	private DepartmentService departmentService;

	@GetMapping("/admin/employee-department-table")
	public String departmentDataTable( Model model) {
		model.addAttribute("listDepartments",departmentService.getAllDepartments());
		return "employee/department_table_list";
	}
	
	@GetMapping("/admin/employee-department-table/export/{format}")
	public String exportDepartmentReport(@PathVariable String format,Model model) throws FileNotFoundException, JRException {
		model.addAttribute("listDepartments",departmentService.exportDepartmentReport(format));
		return "redirect:/admin/employee-department-table";
	}
	
	@Autowired
	private BranchService branchService;
	
	@GetMapping("/admin/employee-branch-table")
	public String branchDataTable(Model model) {
		model.addAttribute("listBranches", branchService.getAllBranches());
		return "employee/branch_table_list";
	}
	
	@GetMapping("/admin/employee-branch-table/export/{format}")
	public String exportBranchReport(@PathVariable String format,Model model) throws FileNotFoundException, JRException {
		model.addAttribute("listBranches",branchService.exportBranchReport(format));
		return "redirect:/admin/employee-branch-table";
	}
	
	
	@GetMapping("/admin/employee-dashboard")
	public String employeeDash() {
		return "employee/employee_admin_dashboard";
	}
	
	@GetMapping("/admin/employee-user-dashboard")
	public String employeeUserDash() {
		return "employee/employee_user_dashboard";
	}
	

	@GetMapping("/admin/employee-addEmp")
	public String employeeAdminAdd() {
		return "employee/employee_add";
	}
	
	
	@GetMapping("/admin/employee-department-add")
	public String departmentAdd() {
		return "employee/add_department";
	}
	
	
	@GetMapping("/admin/employee-branch-add")
	public String branchAdd() {
		return "employee/add_branch";
	}
	
	@GetMapping("/admin/employee-view-employee")
	public String viewEmployee() {
		return "employee/view_employee";
	}
	
	@GetMapping("/admin/employee-view-department")
	public String viewDepartment() {
		return "employee/view_department";
	}
	
	@GetMapping("/admin/employee-view-branch")
	public String viewBranch() {
		return "employee/view_branch";
	}
	
	
	@GetMapping("/admin/403")
	public String error403() {
		return "employee/403";
	}
	
	
}

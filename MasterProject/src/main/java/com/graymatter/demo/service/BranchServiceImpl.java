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

import com.graymatter.demo.model.Branch;
import com.graymatter.demo.repo.BranchRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class BranchServiceImpl implements BranchService {
	
	@Autowired
	private BranchRepository branchRepository;

	// Get all branch method
	@Override
	public List<Branch> getAllBranches() {
		return branchRepository.findAll();
	}

	// Save branch method
	@Override
	public void saveBranch(Branch branch) {
		this.branchRepository.save(branch);
	}

	// Get Branch by ID
	@Override
	public Branch getBranchById(int id) {
		Optional<Branch> optional = branchRepository.findById(id);
		Branch branch = null;
		if(optional.isPresent()) {
			branch = optional.get();
		}else {
			throw new RuntimeException("Branch not found for id :: " + id);
		}
		return branch;
	}
	
	// Delete branch method
	@Override
	public void deleteBranchById(int id) {
		this.branchRepository.deleteById(id);
		
	}

	@Override
	public String exportBranchReport(String format) throws FileNotFoundException, JRException {
		List<Branch> branchList = getAllBranches();	
		String path = "D://JasperReports//"; //File location
		
		File file = ResourceUtils.getFile("classpath:Branches.jrxml"); // Get the pringting format
		JasperReport jasper = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(branchList);		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("Branch", "List");		
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasper, parameters, ds);
		
		if(format.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint, path +"//branches.html"); // File output in an HTML form
		}
		if(format.equalsIgnoreCase("pdf")) {
			JasperExportManager.exportReportToPdfFile(jasperPrint, path +"//branches.pdf"); // File output in a PDF form
		}
		
		return "path : "+path;
	}

}

/*
 * Toan Nguyen
 * Master Project
 * 02/24/2025
 */

package com.graymatter.demo.service;

import java.io.FileNotFoundException;
import java.util.List;

import com.graymatter.demo.model.Branch;

import net.sf.jasperreports.engine.JRException;

public interface BranchService {
	List<Branch> getAllBranches();
	void saveBranch(Branch branch);
	Branch getBranchById(int id);
	void deleteBranchById(int id);
	String exportBranchReport(String format) throws FileNotFoundException, JRException;
}

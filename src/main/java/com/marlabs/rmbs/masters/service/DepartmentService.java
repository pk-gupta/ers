package com.marlabs.rmbs.masters.service;

import java.util.List;

import com.marlabs.rmbs.entities.DepartmentMaster;
@FunctionalInterface
public interface DepartmentService {
	public List<DepartmentMaster> loadDepartment();
}

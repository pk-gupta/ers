package com.marlabs.rmbs.masters.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.marlabs.rmbs.entities.DepartmentMaster;
import com.marlabs.rmbs.masters.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public List<DepartmentMaster> loadDepartment() {
		return (List<DepartmentMaster>) departmentRepository.findAll();
	}

}

package com.marlabs.rmbs.masters.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.marlabs.rmbs.entities.DepartmentMaster;

public interface DepartmentRepository extends JpaRepository<DepartmentMaster,Integer>{

	public DepartmentMaster findByDepatmentId(Integer employeeDepartmentId);

}

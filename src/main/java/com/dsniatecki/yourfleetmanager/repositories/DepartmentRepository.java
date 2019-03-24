package com.dsniatecki.yourfleetmanager.repositories;

import com.dsniatecki.yourfleetmanager.domains.Department;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department, Long> {
}

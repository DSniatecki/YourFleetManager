package com.dsniatecki.yourfleetmanager.services;

import com.dsniatecki.yourfleetmanager.domains.Company;
import com.dsniatecki.yourfleetmanager.domains.Department;

import java.util.List;

public interface DepartmentService {
    Department getById(Long id);
    Department save(Department Department, Long companyId);
    void deleteById(Long id);
}

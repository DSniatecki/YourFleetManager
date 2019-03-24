package com.dsniatecki.yourfleetmanager.services;

import com.dsniatecki.yourfleetmanager.domains.Department;

public interface DepartmentService {
    Department getById(Long id);
    Department save(Department Department, Long companyId);
    void deleteById(Long id);
}

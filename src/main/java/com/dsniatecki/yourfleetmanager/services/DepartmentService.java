package com.dsniatecki.yourfleetmanager.services;

import com.dsniatecki.yourfleetmanager.dto.DepartmentDTO;

public interface DepartmentService {
    DepartmentDTO getById(Long id);
    DepartmentDTO save(DepartmentDTO departmentDTO, Long companyId);
    void deleteById(Long id);
}

package com.dsniatecki.yourfleetmanager.services;


import com.dsniatecki.yourfleetmanager.domains.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompanyService{
    Page<Company> getAllPageable(Pageable pageable);
    List<Company> getAll();
    Company getById(Long id);
    Company save(Company company);
    void deleteById(Long id);
}

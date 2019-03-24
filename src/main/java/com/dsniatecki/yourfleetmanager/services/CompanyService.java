package com.dsniatecki.yourfleetmanager.services;


import com.dsniatecki.yourfleetmanager.domains.Company;

import java.util.List;

public interface CompanyService{
    List<Company> getAll();
    Company getById(Long id);
    Company save(Company company);
    void deleteById(Long id);
}

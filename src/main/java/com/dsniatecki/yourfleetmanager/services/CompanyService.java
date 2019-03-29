package com.dsniatecki.yourfleetmanager.services;


import com.dsniatecki.yourfleetmanager.dto.CompanyDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompanyService{
    Page<CompanyDTO> getAllPageable(Pageable pageable);
    List<CompanyDTO> getAll();
    CompanyDTO getById(Long id);
    CompanyDTO save(CompanyDTO company);
    void deleteById(Long id);
}

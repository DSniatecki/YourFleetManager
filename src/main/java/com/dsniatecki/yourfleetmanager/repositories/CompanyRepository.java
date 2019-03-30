package com.dsniatecki.yourfleetmanager.repositories;

import com.dsniatecki.yourfleetmanager.entities.Company;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CompanyRepository extends PagingAndSortingRepository<Company, Long> {
}

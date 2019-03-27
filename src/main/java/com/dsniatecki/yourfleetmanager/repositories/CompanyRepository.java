package com.dsniatecki.yourfleetmanager.repositories;

import com.dsniatecki.yourfleetmanager.domains.Company;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CompanyRepository extends PagingAndSortingRepository<Company, Long> {
}

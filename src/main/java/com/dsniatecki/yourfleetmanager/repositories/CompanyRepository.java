package com.dsniatecki.yourfleetmanager.repositories;

import com.dsniatecki.yourfleetmanager.domains.Company;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company, Long> {
}

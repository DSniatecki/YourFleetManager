package com.dsniatecki.yourfleetmanager.services;

import com.dsniatecki.yourfleetmanager.domains.Company;
import com.dsniatecki.yourfleetmanager.exceptions.NotFoundException;
import com.dsniatecki.yourfleetmanager.repositories.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }


    @Override
    public List<Company> getAll() {
        List<Company> companies = new ArrayList<>();
        companyRepository.findAll().iterator().forEachRemaining(companies::add);
        return companies;
    }

    @Override
    public Company getById(Long id) throws NotFoundException{
        Optional<Company> companyOptional = companyRepository.findById(id);
        if(!companyOptional.isPresent()) {
            throw new NotFoundException("Company[ id: " + id + " ] was not found .");
        }
        return companyOptional.get();
    }

    @Override
    public Company save(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public void deleteById(Long id) throws NotFoundException{
        try{
            companyRepository.deleteById(id);
        }catch(IllegalArgumentException e ){
            throw new NotFoundException("Company[ id: " + id + " ] was not found .");
        }
    }

    //---------------------------------------------------------------------------------------------


    @Override
    public List<Company> getAllBy(String parameter ) {
        List<Company> companies = new ArrayList<>();
        companyRepository.findAll().iterator().forEachRemaining(companies::add);
        return companies;
    }


}

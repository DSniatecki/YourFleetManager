package com.dsniatecki.yourfleetmanager.services;

import com.dsniatecki.yourfleetmanager.domains.Company;
import com.dsniatecki.yourfleetmanager.domains.Department;
import com.dsniatecki.yourfleetmanager.exceptions.NotFoundException;
import com.dsniatecki.yourfleetmanager.repositories.CompanyRepository;
import com.dsniatecki.yourfleetmanager.repositories.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
class DepartmentServiceImpl implements DepartmentService{

    private final DepartmentRepository departmentRepository;
    private final CompanyRepository companyRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, CompanyRepository companyRepository){
        this.departmentRepository = departmentRepository;
        this.companyRepository = companyRepository;
    }


    @Override
    public Department getById(Long id) throws NotFoundException{
        Optional<Department> departmentOptional =  departmentRepository.findById(id);
        if(!departmentOptional.isPresent()) {
            throw new NotFoundException("Department[ id: " + id + " ] was not found .");
        }
        return departmentOptional.get();
    }

    @Override
    public Department save(Department department, Long companyId) {
        Optional<Company> companyOptional = companyRepository.findById(companyId);

        if(!(companyOptional.isPresent())) {
            throw new NotFoundException("Company[ id: " + companyId + " ] was not found .");
        }
        department.setCompany(companyOptional.get());
        return departmentRepository.save(department);
    }

    @Override
    public void deleteById(Long id) throws NotFoundException{
        try{
            departmentRepository.deleteById(id);
        }catch(IllegalArgumentException e ){
            throw new NotFoundException("Department[ id: " + id + " ] was not found .");
        }
    }
}

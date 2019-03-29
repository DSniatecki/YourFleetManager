package com.dsniatecki.yourfleetmanager.services;

import com.dsniatecki.yourfleetmanager.domains.Company;
import com.dsniatecki.yourfleetmanager.domains.Department;
import com.dsniatecki.yourfleetmanager.dto.DepartmentDTO;
import com.dsniatecki.yourfleetmanager.exceptions.NotFoundException;
import com.dsniatecki.yourfleetmanager.repositories.CompanyRepository;
import com.dsniatecki.yourfleetmanager.repositories.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class DepartmentServiceImpl implements DepartmentService{

    private final DepartmentRepository departmentRepository;
    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, CompanyRepository companyRepository){
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.departmentRepository = departmentRepository;
        this.companyRepository = companyRepository;
    }


    @Override
    public DepartmentDTO getById(Long id) throws NotFoundException{
        Optional<Department> departmentOptional =  departmentRepository.findById(id);
        if(!departmentOptional.isPresent()) {
            throw new NotFoundException("Department[ id: " + id + " ] was not found .");
        }
        return modelMapper.map(departmentOptional.get(), DepartmentDTO.class);
    }

    @Override
    public DepartmentDTO save(DepartmentDTO departmentDTO, Long companyId) {
        Optional<Company> companyOptional = companyRepository.findById(companyId);

        if(!(companyOptional.isPresent())) {
            throw new NotFoundException("Company[ id: " + companyId + " ] was not found .");
        }
        Department department = modelMapper.map(departmentDTO, Department.class);
        department.setCompany(companyOptional.get());
        Department savedDepartment =  departmentRepository.save(department);
        return modelMapper.map(savedDepartment, DepartmentDTO.class);
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

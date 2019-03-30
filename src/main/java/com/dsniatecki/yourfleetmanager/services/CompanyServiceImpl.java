package com.dsniatecki.yourfleetmanager.services;

import com.dsniatecki.yourfleetmanager.entities.Company;
import com.dsniatecki.yourfleetmanager.dto.CompanyDTO;
import com.dsniatecki.yourfleetmanager.exceptions.NotFoundException;
import com.dsniatecki.yourfleetmanager.repositories.CompanyRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;

    public CompanyServiceImpl(CompanyRepository companyRepository){
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.companyRepository = companyRepository;
    }

    @Override
    public Page<CompanyDTO> getAllPageable(Pageable pageable) {
        Page<Company> companies = companyRepository.findAll(pageable);
        Page<CompanyDTO> companyDTOs = companies.map(new Function<Company, CompanyDTO>() {
            @Override
            public CompanyDTO apply(Company company) {
                return modelMapper.map(company, CompanyDTO.class);
            }
        });

        return companyDTOs;

    }

    @Override
    public List<CompanyDTO> getAll() {
        List<Company> companies = new ArrayList<>();
        companyRepository.findAll().iterator().forEachRemaining(companies::add);
        return companies.stream().map( company -> modelMapper.map(company, CompanyDTO.class) ).collect(Collectors.toList());
    }

    @Override
    public CompanyDTO getById(Long id) throws NotFoundException{
        Optional<Company> companyOptional = companyRepository.findById(id);
        if(!companyOptional.isPresent()) {
            throw new NotFoundException("Company[ id: " + id + " ] was not found .");
        }
        return modelMapper.map(companyOptional.get(), CompanyDTO.class);
    }

    @Override
    public CompanyDTO save(CompanyDTO companyDTO) {
        Company company = modelMapper.map(companyDTO, Company.class);
        Company savedCompany = companyRepository.save(company);
        return modelMapper.map(savedCompany, CompanyDTO.class);
    }

    @Override
    public void deleteById(Long id) throws NotFoundException{
        try{
            companyRepository.deleteById(id);
        }catch(IllegalArgumentException e ){
            throw new NotFoundException("Company[ id: " + id + " ] was not found .");
        }
    }

}

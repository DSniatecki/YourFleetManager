package com.dsniatecki.yourfleetmanager.services;

import com.dsniatecki.yourfleetmanager.entities.Company;
import com.dsniatecki.yourfleetmanager.dto.CompanyDTO;
import com.dsniatecki.yourfleetmanager.exceptions.NotFoundException;
import com.dsniatecki.yourfleetmanager.repositories.CompanyRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class CompanyServiceImplTest {


    CompanyServiceImpl companyService;

    @Mock
    CompanyRepository companyRepository;

    private ModelMapper modelMapper;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        companyService = new CompanyServiceImpl(companyRepository);

        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }


    @Test
    public void testGetByIdSucces() throws  Exception{
        Company company = new Company();
        company.setId(100L);
        when(companyRepository.findById(anyLong())).thenReturn(Optional.of(company));
        CompanyDTO returnedCompanyDTO = companyService.getById(anyLong());

        assertNotNull(returnedCompanyDTO);
        assertSame(returnedCompanyDTO.getId(), company.getId());
        verify(companyRepository, times(1)).findById(anyLong());
    }

    @Test(expected = NotFoundException.class)
    public void testGetByIdNotFound() throws Exception{
        when(companyRepository.findById(anyLong())).thenThrow(NotFoundException.class);
        CompanyDTO returnedCompanyDTO = companyService.getById(anyLong());
    }

    @Test
    public void testFindAll() throws Exception{
        Company company1 = new Company();
        company1.setId(23232L);
        Company company2 = new Company();
        company2.setId(67654L);
        when(companyRepository.findAll()).thenReturn(Arrays.asList(company1, company2));
        List<CompanyDTO> returnedCompanies = companyService.getAll();
        assertEquals(2, returnedCompanies.size());
        assertEquals(company1.getId(), returnedCompanies.get(0).getId());
        assertEquals(company2.getId(), returnedCompanies.get(1).getId());
    }

    @Test
    public void testSave() throws Exception{
        Company company = new Company();
        company.setId(2222L);
        when(companyRepository.save(any())).thenReturn(company);
        CompanyDTO companyDTO = modelMapper.map(company, CompanyDTO.class);
        CompanyDTO savedCompanyDTO = companyService.save(companyDTO);
        assertEquals(company.getId(), savedCompanyDTO.getId());
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteByIdNotFound() throws Exception{
        doThrow(IllegalArgumentException.class).when(companyRepository).deleteById(anyLong());
        companyService.deleteById(anyLong());
    }


}
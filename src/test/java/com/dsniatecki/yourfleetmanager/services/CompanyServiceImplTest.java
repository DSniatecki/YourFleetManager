package com.dsniatecki.yourfleetmanager.services;

import com.dsniatecki.yourfleetmanager.domains.Company;
import com.dsniatecki.yourfleetmanager.exceptions.NotFoundException;
import com.dsniatecki.yourfleetmanager.repositories.CompanyRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class CompanyServiceImplTest {


    CompanyServiceImpl companyService;

    @Mock
    CompanyRepository companyRepository;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        companyService = new CompanyServiceImpl(companyRepository);
    }


    @Test
    public void testGetByIdSucces() throws  Exception{
        Company company = new Company();
        when(companyRepository.findById(anyLong())).thenReturn(Optional.of(company));
        Company returnedCompany = companyService.getById(anyLong());

        assertNotNull(returnedCompany);
        assertSame(returnedCompany, company);
        verify(companyRepository, times(1)).findById(anyLong());
    }

    @Test(expected = NotFoundException.class)
    public void testGetByIdNotFound() throws Exception{
        when(companyRepository.findById(anyLong())).thenThrow(NotFoundException.class);
        Company returnedCompany = companyService.getById(anyLong());
    }

    @Test
    public void testFindAll() throws Exception{
        Company company1 = new Company();
        Company company2 = new Company();
        when(companyRepository.findAll()).thenReturn(Arrays.asList(company1, company2));
        List<Company> returnedCompanies = companyService.getAll();
        assertEquals(2, returnedCompanies.size());
        assertEquals(company1, returnedCompanies.get(0));
        assertEquals(company2, returnedCompanies.get(1));
    }

    @Test
    public void testSave() throws Exception{
        Company company = new Company();
        when(companyRepository.save(any())).thenReturn(company);
        Company savedCompany = companyService.save(company);
        assertEquals(company, savedCompany);
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteByIdNotFound() throws Exception{
        doThrow(IllegalArgumentException.class).when(companyRepository).deleteById(anyLong());
        companyService.deleteById(anyLong());
    }


}
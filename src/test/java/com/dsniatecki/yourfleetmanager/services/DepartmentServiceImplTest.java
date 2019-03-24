package com.dsniatecki.yourfleetmanager.services;

import com.dsniatecki.yourfleetmanager.domains.Company;
import com.dsniatecki.yourfleetmanager.domains.Department;
import com.dsniatecki.yourfleetmanager.exceptions.NotFoundException;
import com.dsniatecki.yourfleetmanager.repositories.CompanyRepository;
import com.dsniatecki.yourfleetmanager.repositories.DepartmentRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class DepartmentServiceImplTest {

    DepartmentServiceImpl departmentService;

    @Mock
    DepartmentRepository departmentRepository;

    @Mock
    CompanyRepository companyRepository;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        departmentService = new DepartmentServiceImpl(departmentRepository, companyRepository);
    }

    @Test
    public void testGetByIdSuccess() throws Exception{
        Department department = new Department();
        when(departmentRepository.findById(anyLong())).thenReturn(Optional.of(department));
        Department returnedDepartment = departmentService.getById(anyLong());

        assertNotNull(returnedDepartment);
        assertSame(returnedDepartment, department);
        verify(departmentRepository, times(1)).findById(anyLong());
    }

    @Test(expected = NotFoundException.class)
    public void testGetByIdNotFound() throws Exception{
        when(departmentRepository.findById(anyLong())).thenThrow(NotFoundException.class);
        Department returnedDepartment = departmentService.getById(anyLong());
    }


    @Test
    public void testSave() throws Exception{
        Department department = new Department();
        Company company = new Company();
        when(companyRepository.findById(anyLong())).thenReturn(Optional.of(company));
        when(departmentRepository.save(any())).thenReturn(department);
        Department savedDepartment = departmentService.save(department, 1L);
        assertEquals(department, savedDepartment);
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteById() throws Exception{
        doThrow(IllegalArgumentException.class).when(departmentRepository).deleteById(anyLong());
        departmentService.deleteById(anyLong());
    }
}
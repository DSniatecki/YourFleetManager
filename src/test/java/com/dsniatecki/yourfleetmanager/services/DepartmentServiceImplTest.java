package com.dsniatecki.yourfleetmanager.services;

import com.dsniatecki.yourfleetmanager.entities.Company;
import com.dsniatecki.yourfleetmanager.entities.Department;
import com.dsniatecki.yourfleetmanager.dto.DepartmentDTO;
import com.dsniatecki.yourfleetmanager.exceptions.NotFoundException;
import com.dsniatecki.yourfleetmanager.repositories.CompanyRepository;
import com.dsniatecki.yourfleetmanager.repositories.DepartmentRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

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

    private ModelMapper modelMapper;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        departmentService = new DepartmentServiceImpl(departmentRepository, companyRepository);

        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Test
    public void testGetByIdSuccess() throws Exception{
        Department department = new Department();
        department.setId(5673L);
        when(departmentRepository.findById(anyLong())).thenReturn(Optional.of(department));

        DepartmentDTO returnedDepartmentDTO = departmentService.getById(anyLong());

        assertNotNull(returnedDepartmentDTO);
        assertSame(returnedDepartmentDTO.getId(), department.getId());
        verify(departmentRepository, times(1)).findById(anyLong());
    }

    @Test(expected = NotFoundException.class)
    public void testGetByIdNotFound() throws Exception{
        when(departmentRepository.findById(anyLong())).thenThrow(NotFoundException.class);
        DepartmentDTO returnedDepartmentDTO = departmentService.getById(anyLong());
    }


    @Test
    public void testSave() throws Exception{
        Department department = new Department();
        department.setId(5673L);
        Company company = new Company();
        company.setId(8888L);
        when(companyRepository.findById(anyLong())).thenReturn(Optional.of(company));
        when(departmentRepository.save(any())).thenReturn(department);

        DepartmentDTO departmentDTO = modelMapper.map(department, DepartmentDTO.class);
        DepartmentDTO savedDepartmentDTO = departmentService.save(departmentDTO, anyLong());

        assertEquals(department.getId(), savedDepartmentDTO.getId());
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteById() throws Exception{
        doThrow(IllegalArgumentException.class).when(departmentRepository).deleteById(anyLong());
        departmentService.deleteById(anyLong());
    }
}
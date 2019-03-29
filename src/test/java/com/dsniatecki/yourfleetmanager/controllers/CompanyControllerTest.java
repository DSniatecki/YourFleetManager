package com.dsniatecki.yourfleetmanager.controllers;


import com.dsniatecki.yourfleetmanager.dto.CompanyDTO;
import com.dsniatecki.yourfleetmanager.services.CompanyService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CompanyControllerTest {

    private CompanyController companyController;

    @Mock
    CompanyService companyService;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        companyController = new CompanyController(companyService);
        mockMvc = MockMvcBuilders.standaloneSetup(companyController).build();
    }

    @Test
    public void testShowListWithPagination() throws Exception{
        List<CompanyDTO> companyDTOs = new ArrayList<>();
        Page expectedPage = new PageImpl(companyDTOs);

        when(companyService.getAllPageable(any(Pageable.class))).thenReturn(expectedPage);

        mockMvc.perform(get("/company/")

        )
                .andExpect( status().isOk())
                .andExpect( model().attributeExists("companiesPage"))
                .andExpect( view().name("company/company-list-page") );

        verify(companyService, times(1)).getAllPageable(any());

    }

    @Test
    public void testShowListWithPaginationSecond() throws Exception{
        List<CompanyDTO> companyDTOs = new ArrayList<>();
        Page expectedPage = new PageImpl(companyDTOs);

        when(companyService.getAllPageable(any(Pageable.class))).thenReturn(expectedPage);

        mockMvc.perform(get("/company/?page=2")

        )
                .andExpect( status().isOk())
                .andExpect( model().attributeExists("companiesPage"))
                .andExpect( model().attributeExists("prevCompaniesNumber"))
                .andExpect( model().attributeExists("pageNumbers"))
                .andExpect( view().name("company/company-list-page") );

        verify(companyService, times(1)).getAllPageable(any());

    }

    @Test
    public void testShowAll() throws Exception{
        when(companyService.getAll()).thenReturn(new ArrayList<CompanyDTO>());

        mockMvc.perform(get("/company/all"))
                .andExpect( status().isOk())
                .andExpect( model().attributeExists("companies"))
                .andExpect( view().name("company/company-list") );

        verify(companyService, times(1)).getAll();

    }


    @Test
    public void testAddNew() throws Exception{
        mockMvc.perform(get("/company/add"))
                .andExpect( status().isOk())
                .andExpect( model().attributeExists("company"))
                .andExpect( view().name("company/company-form") );
    }

    @Test
    public void testShowDetails() throws Exception{

        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setId(1L);

        when(companyService.getById(anyLong())).thenReturn(companyDTO);

        mockMvc.perform(get("/company/{id}/details", 1L))
                .andExpect( status().isOk())
                .andExpect( model().attributeExists("company"))
                .andExpect( view().name("company/company-details"));

        verify(companyService, times(1)).getById(anyLong());
    }


    @Test
    public void testUpdate() throws Exception{

        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setId(1L);

        when(companyService.getById(anyLong())).thenReturn(companyDTO);

        mockMvc.perform(get("/company/{id}/update", 1L))
                .andExpect( status().isOk())
                .andExpect( model().attributeExists("company"))
                .andExpect( view().name("company/company-form") );

        verify(companyService, times(1)).getById(anyLong());
    }

    @Test
    public void testDelete() throws Exception{
        mockMvc.perform(get("/company/{id}/delete", 1L))
                .andExpect( status().is3xxRedirection())
                .andExpect( view().name("redirect:/company/"));
    }

    @Test
    public void testSave() throws Exception{

        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setId(1L);
        when(companyService.save(any())).thenReturn(companyDTO);

        mockMvc.perform(post("/company/save")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "1")
        )
                .andExpect( status().isOk())
                .andExpect( view().name("company/company-details") );

        verify(companyService, times(1)).save(any());
    }



}
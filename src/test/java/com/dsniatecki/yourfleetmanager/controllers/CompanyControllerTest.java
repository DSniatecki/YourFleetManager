package com.dsniatecki.yourfleetmanager.controllers;


import com.dsniatecki.yourfleetmanager.domains.Company;
import com.dsniatecki.yourfleetmanager.services.CompanyService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CompanyControllerTest {

    CompanyController companyController;

    @Mock
    CompanyService companyService;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        companyController = new CompanyController(companyService);
        mockMvc = MockMvcBuilders.standaloneSetup(companyController).build();
    }

    @Test
    public void testShowAll() throws Exception{
        when(companyService.getAll()).thenReturn(new ArrayList<Company>());

        mockMvc.perform(get("/company/"))
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

        Company company = new Company();
        company.setId(1L);

        when(companyService.getById(anyLong())).thenReturn(company);

        mockMvc.perform(get("/company/{id}/details", 1L))
                .andExpect( status().isOk())
                .andExpect( model().attributeExists("company"))
                .andExpect( view().name("company/company-details"));

        verify(companyService, times(1)).getById(anyLong());
    }


    @Test
    public void testUpdate() throws Exception{

        Company company = new Company();
        company.setId(1L);

        when(companyService.getById(anyLong())).thenReturn(company);

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

        Company company = new Company();
        company.setId(1L);
        when(companyService.save(any())).thenReturn(company);

        mockMvc.perform(post("/company/save")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "1")
        )
                .andExpect( status().isOk())
                .andExpect( view().name("company/company-details") );

        verify(companyService, times(1)).save(any());
    }



}
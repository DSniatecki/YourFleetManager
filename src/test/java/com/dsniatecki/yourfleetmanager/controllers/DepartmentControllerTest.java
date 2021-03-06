package com.dsniatecki.yourfleetmanager.controllers;


import com.dsniatecki.yourfleetmanager.dto.DepartmentDTO;
import com.dsniatecki.yourfleetmanager.services.DepartmentService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class DepartmentControllerTest {

    private DepartmentController departmentController;

    @Mock
    DepartmentService departmentService;

    private  MockMvc mockMvc;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        departmentController = new DepartmentController(departmentService);
        mockMvc = MockMvcBuilders.standaloneSetup(departmentController).build();
    }

    @Test
    public void testAddNew() throws  Exception{
        mockMvc.perform(get("/company/{id}/department/add", 1L))
                .andExpect( status().isOk())
                .andExpect( model().attributeExists("department"))
                .andExpect( view().name("department/department-form"));
    }

    @Test
    public void testUpdate() throws  Exception{
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(1L);
        when(departmentService.getById(anyLong())).thenReturn(departmentDTO);

        mockMvc.perform(get("/company/{companyId}/department/{departmentId}/update", 1L , 1L ))
                .andExpect( status().isOk())
                .andExpect( model().attributeExists("department"))
                .andExpect( view().name("department/department-form"));

        verify(departmentService, times(1)).getById(anyLong());
    }

    @Test
    public void testSave() throws  Exception {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(1L);

        when(departmentService.save(any(), anyLong())).thenReturn(departmentDTO);

        mockMvc.perform(post("/company/{companyId}/department/save", 1L)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "1")
                .param("name", "FunnyCompany")
                .param("contactDetails.telephoneNumber", "785-435-234")
                .param("contactDetails.emailAddress", "superemail@gmail.com")
        )
                .andExpect( status().isOk())
                .andExpect( view().name("department/department-details") );

        verify(departmentService, times(1)).save(any(), anyLong());
    }

    @Test
    public void testDelete() throws  Exception{
        mockMvc.perform(get("/company/{companyId}/department/{departmentId}/delete", 1L, 1L))
                .andExpect( status().is3xxRedirection())
                .andExpect( view().name("redirect:/company/{companyId}/department"));
    }
}
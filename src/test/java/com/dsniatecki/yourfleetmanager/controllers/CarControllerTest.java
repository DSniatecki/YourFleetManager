package com.dsniatecki.yourfleetmanager.controllers;

import com.dsniatecki.yourfleetmanager.domains.Car;
import com.dsniatecki.yourfleetmanager.services.CarService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CarControllerTest {

    CarController carController;

    @Mock
    CarService carService;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        carController = new CarController(carService);
        mockMvc = MockMvcBuilders.standaloneSetup(carController).build();
    }

    @Test
    public void testShowDetails() throws  Exception{
        Car car = new Car();
        when(carService.getById(anyLong())).thenReturn(car);

        mockMvc.perform(get("/company/{companyId}/department/{departmentId}/car/{carId}/details", 1L, 1L, 1L))
                .andExpect( status().isOk())
                .andExpect( model().attributeExists("car"))
                .andExpect( model().attributeExists("companyId"))
                .andExpect( view().name("car/car-details"));

        verify(carService, times(1)).getById(anyLong());
    }

    @Test
    public void testDeleteById() throws  Exception{
        mockMvc.perform(get("/company/{companyId}/department/{departmentId}/car/{carId}/delete", 1L, 1L, 1L))
                .andExpect( status().is3xxRedirection())
                .andExpect( view().name("redirect:/company/{companyId}/department/{departmentId}/car"));
    }

    @Test
    public void testAddNew() throws  Exception{
        mockMvc.perform(get("/company/{companyId}/department/{departmentId}/car/add", 1L, 1L))
                .andExpect( status().isOk())
                .andExpect( model().attributeExists("car"))
                .andExpect( model().attributeExists("companyId"))
                .andExpect( model().attributeExists("departmentId"))
                .andExpect( view().name("car/car-form"));
    }

    @Test
    public void testSave() throws  Exception{
        Car car = new Car();
        car.setId(1L);
        when(carService.saveWithDepartment(any(), anyLong())).thenReturn(car);

        mockMvc.perform(post("/company/{companyId}/department/{departmentId}/car/save", 1L, 1L)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "1")
        )
                .andExpect( status().isOk())
                .andExpect( model().attributeExists("car"))
                .andExpect( model().attributeExists("companyId"))
                .andExpect( view().name("car/car-details"));

        verify(carService, times(1)).saveWithDepartment(any(), anyLong());
    }

    @Test
    public void testUpdate() throws  Exception{
        Car car = new Car();
        when(carService.getById(anyLong())).thenReturn(car);

        mockMvc.perform(get("/company/{companyId}/department/{departmentId}/car/{carId}/update", 1L, 1L, 1L))
                .andExpect( status().isOk())
                .andExpect( model().attributeExists("car"))
                .andExpect( model().attributeExists("companyId"))
                .andExpect( model().attributeExists("departmentId"))
                .andExpect( view().name("car/car-form"));

        verify(carService, times(1)).getById(anyLong());
    }


}

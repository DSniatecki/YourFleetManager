package com.dsniatecki.yourfleetmanager.services;

import com.dsniatecki.yourfleetmanager.domains.Car;
import com.dsniatecki.yourfleetmanager.domains.Department;
import com.dsniatecki.yourfleetmanager.exceptions.NotFoundException;
import com.dsniatecki.yourfleetmanager.repositories.CarRepository;
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

public class CarServiceImplTest {


    CarServiceImpl carService;

    @Mock
    CarRepository carRepository;

    @Mock
    DepartmentRepository departmentRepository;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        carService = new CarServiceImpl(carRepository, departmentRepository);
    }


    @Test
    public void testGetByIdSucces() throws  Exception{
        Car car = new Car();
        when(carRepository.findById(anyLong())).thenReturn(Optional.of(car));
        Car returnedCar = carService.getById(anyLong());

        assertNotNull(returnedCar);
        assertSame(returnedCar, car);
        verify(carRepository, times(1)).findById(anyLong());
    }

    @Test(expected = NotFoundException.class)
    public void testGetByIdNotFound() throws Exception{
        when(carRepository.findById(anyLong())).thenThrow(NotFoundException.class);
        Car returnedCar = carService.getById(anyLong());
    }


    @Test
    public void testSave() throws Exception{
        Car car = new Car();
        Department department = new Department();
        when(departmentRepository.findById(anyLong())).thenReturn(Optional.of(department));
        when(carRepository.save(any())).thenReturn(car);
        Car savedCar = carService.saveWithDepartment(car, anyLong());
        assertEquals(car, savedCar);
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteByIdNotFound() throws Exception{
        doThrow(IllegalArgumentException.class).when(carRepository).deleteById(anyLong());
        carService.deleteById(anyLong());
    }


}
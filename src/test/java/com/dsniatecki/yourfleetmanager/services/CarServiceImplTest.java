package com.dsniatecki.yourfleetmanager.services;

import com.dsniatecki.yourfleetmanager.entities.Car;
import com.dsniatecki.yourfleetmanager.entities.Department;
import com.dsniatecki.yourfleetmanager.dto.CarDTO;
import com.dsniatecki.yourfleetmanager.exceptions.NotFoundException;
import com.dsniatecki.yourfleetmanager.repositories.CarRepository;
import com.dsniatecki.yourfleetmanager.repositories.DepartmentRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.Optional;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class CarServiceImplTest {


    CarServiceImpl carService;

    @Mock
    CarRepository carRepository;

    @Mock
    DepartmentRepository departmentRepository;

    private ModelMapper modelMapper;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        carService = new CarServiceImpl(carRepository, departmentRepository);

        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }


    @Test
    public void testGetByIdSucces() throws  Exception{
        Car car = new Car();
        car.setId(5673L);
        when(carRepository.findById(anyLong())).thenReturn(Optional.of(car));
        CarDTO returnedCarDTO = carService.getById(anyLong());

        assertNotNull(returnedCarDTO);
        assertSame(returnedCarDTO.getId(), modelMapper.map(car, CarDTO.class).getId());
        verify(carRepository, times(1)).findById(anyLong());
    }

    @Test(expected = NotFoundException.class)
    public void testGetByIdNotFound() throws Exception{
        when(carRepository.findById(anyLong())).thenThrow(NotFoundException.class);
        CarDTO returnedCarDTO = carService.getById(anyLong());
    }


    @Test
    public void testSave() throws Exception{
        Car car = new Car();
        car.setId(5673L);
        Department department = new Department();
        department.setId(8888L);
        when(departmentRepository.findById(anyLong())).thenReturn(Optional.of(department));
        when(carRepository.save(any())).thenReturn(car);
        CarDTO carDTO = modelMapper.map(car, CarDTO.class);
        CarDTO savedCarDTO = carService.saveWithDepartment(carDTO, anyLong());
        assertSame(carDTO.getId(), savedCarDTO.getId() );
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteByIdNotFound() throws Exception{
        doThrow(IllegalArgumentException.class).when(carRepository).deleteById(anyLong());
        carService.deleteById(anyLong());
    }


}
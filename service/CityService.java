package com.hotelmansys.service;

import com.hotelmansys.entity.City;
import com.hotelmansys.exception.ResourceNotFound;
import com.hotelmansys.payload.CityDto;
import com.hotelmansys.repository.CityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {

    private CityRepository cityRepository;
    private ModelMapper modelMapper;

    public CityService(CityRepository cityRepository, ModelMapper modelMapper) {
        this.cityRepository = cityRepository;
        this.modelMapper = modelMapper;
    }

    public CityDto createCity(CityDto cityDto){
        City city = mapToEntity(cityDto);

        City save = cityRepository.save(city);

       return mapToDto(save);
    }

    public String deleteCity(Long id){
        City provideValidId = cityRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("provide valid id")
        );
        cityRepository.deleteById(id);
        return "city deleted";
    }

    public List<CityDto> getAllCity(){

        List<City> all = cityRepository.findAll();

        return all.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public City mapToEntity(CityDto cityDto){
        return modelMapper.map(cityDto,City.class);
    }

    public CityDto mapToDto(City city){
        return modelMapper.map(city, CityDto.class);
    }



}

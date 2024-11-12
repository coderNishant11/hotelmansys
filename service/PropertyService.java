package com.hotelmansys.service;


import com.hotelmansys.config.MapToEntityOrDto;
import com.hotelmansys.entity.Property;
import com.hotelmansys.exception.ResourceNotFound;
import com.hotelmansys.payload.PropertyDto;
import com.hotelmansys.repository.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PropertyService {
    private PropertyRepository propertyRepository;

    private MapToEntityOrDto mapToEntityOrDto;

    public PropertyService(PropertyRepository propertyRepository, MapToEntityOrDto mapToEntityOrDto) {
        this.propertyRepository = propertyRepository;
        this.mapToEntityOrDto = mapToEntityOrDto;
    }


    public PropertyDto saveProperty(PropertyDto propertyDto) {

        Property property = mapToEntityOrDto.mapToProperty(propertyDto);
        Property save = propertyRepository.save(property);
        return mapToEntityOrDto.mapToPropertyDto(save);
    }

    public void deleteProperty(Long id) {
        Property property = propertyRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Please provide valid id")
        );
        propertyRepository.deleteById(id);

    }

    public List<PropertyDto> getAll() {
        List<Property> all = propertyRepository.findAll();
        return all.stream().map(e->mapToEntityOrDto.mapToPropertyDto(e)).collect(Collectors.toList());
    }

    public PropertyDto updateProperty(PropertyDto propertyDto, Long id) {


        Property property = propertyRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Please provide valid id")
        );
           property.setName(propertyDto.getName());
           property.setNo_of_guest(propertyDto.getNo_of_guest());
           property.setNo_of_bedrooms(propertyDto.getNo_of_bedrooms());
           property.setNo_of_beds(propertyDto.getNo_of_beds());
           property.setNo_of_bathrooms(propertyDto.getNo_of_bathrooms());
           property.setId(id);
            Property save = propertyRepository.save(property);

           return mapToEntityOrDto.mapToPropertyDto(save);


    }

    public List<PropertyDto> searchHotels(String name) {
        List<Property> properties = propertyRepository.searchHotels(name);

        return properties.stream().map(e->mapToEntityOrDto.mapToPropertyDto(e)).collect(Collectors.toList());
    }
}

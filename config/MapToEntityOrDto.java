package com.hotelmansys.config;

import com.hotelmansys.entity.City;
import com.hotelmansys.entity.Country;
import com.hotelmansys.entity.Property;
import com.hotelmansys.exception.ResourceNotFound;
import com.hotelmansys.payload.PropertyDto;

import com.hotelmansys.repository.CityRepository;
import com.hotelmansys.repository.CountryRepository;
import com.hotelmansys.repository.PropertyRepository;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
public class MapToEntityOrDto {

    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;


    public MapToEntityOrDto(CityRepository cityRepository,
                            CountryRepository countryRepository,
                            PropertyRepository propertyRepository) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;

    }

    public Property mapToProperty(PropertyDto propertyDto){
        Property property = new Property();

        property.setName(propertyDto.getName());
        property.setNo_of_guest(propertyDto.getNo_of_guest());
        property.setNo_of_bedrooms(propertyDto.getNo_of_bedrooms());
        property.setNo_of_beds(propertyDto.getNo_of_beds());
        property.setNo_of_bathrooms(propertyDto.getNo_of_bathrooms());
        City city = cityRepository.findCityByName(propertyDto.getCityName()).orElseThrow(
                ()->new ResourceNotFound("Service not available in this city")
        );
        property.setCity(city);
        Country country = countryRepository.findCountryBYName(propertyDto.getCountryName()).orElseThrow(
                ()->new ResourceNotFound("Service not available in this country")
        );
        property.setCountry(country);

        return property;

    }

    public PropertyDto mapToPropertyDto(Property property){
        PropertyDto propertyDto = new PropertyDto();

        propertyDto.setName(property.getName());
        propertyDto.setNo_of_guest(property.getNo_of_guest());
        propertyDto.setNo_of_bedrooms(property.getNo_of_bedrooms());
        propertyDto.setNo_of_beds(property.getNo_of_beds());
        propertyDto.setNo_of_bathrooms(property.getNo_of_bathrooms());
        propertyDto.setCityName(property.getCity().getName());
        propertyDto.setCountryName(property.getCountry().getName());

        return propertyDto;
    }

}

package com.hotelmansys.service;

import com.hotelmansys.entity.Country;
import com.hotelmansys.exception.ResourceNotFound;
import com.hotelmansys.payload.CountryDto;
import com.hotelmansys.repository.CountryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService {

    private CountryRepository countryRepository;
    private ModelMapper modelMapper;

    public CountryService(CountryRepository countryRepository, ModelMapper modelMapper) {

        this.countryRepository = countryRepository;
        this.modelMapper = modelMapper;
    }

    public CountryDto addCountry(CountryDto countryDto){
        Country country = mapToEntity(countryDto);
        Country save = countryRepository.save(country);

        return mapToDto(save);
    }

    public List<CountryDto> getAllCountry(){
        List<Country> all = countryRepository.findAll();

       return all.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public String deleteCountry(Long id){
        countryRepository.findById(id).orElseThrow(
                ()->new ResourceNotFound("Please provide valid country id")
        );
        countryRepository.deleteById(id);

        return "Country deleted";
    }

    public Country mapToEntity(CountryDto countryDto){
        return modelMapper.map(countryDto, Country.class);
    }

    public CountryDto mapToDto(Country country){
        return modelMapper.map(country, CountryDto.class);
    }


}

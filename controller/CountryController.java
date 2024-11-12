package com.hotelmansys.controller;

import com.hotelmansys.payload.CountryDto;
import com.hotelmansys.service.CountryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/countries")
public class CountryController {

    private CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping("/addCountry")
    public ResponseEntity<?> addCountry(@Valid @RequestBody CountryDto countryDto, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        CountryDto countryDto1 = countryService.addCountry(countryDto);

        return new ResponseEntity<>(countryDto1,HttpStatus.CREATED);
    }

    @GetMapping("/allCountry")
    public ResponseEntity<List<CountryDto>> getALl(){
        List<CountryDto> allCountry = countryService.getAllCountry();

        return new ResponseEntity<>(allCountry, HttpStatus.OK);

    }

    @DeleteMapping("/deleteCountry")
    public ResponseEntity<String> deleteCountry(@RequestParam Long id){
        String s = countryService.deleteCountry(id);

        return new ResponseEntity<>(s,HttpStatus.OK);
    }
}

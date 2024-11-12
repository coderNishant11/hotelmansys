package com.hotelmansys.controller;

import com.hotelmansys.payload.CityDto;
import com.hotelmansys.service.CityService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cities")
public class CityController {

    private CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping("/addCity")
    public ResponseEntity<?> addCity(@Valid @RequestBody CityDto cityDto, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        CityDto city = cityService.createCity(cityDto);

        return new ResponseEntity<>(city,HttpStatus.CREATED);

    }

    @GetMapping("/getAllCity")
    public ResponseEntity<List<CityDto>> getAll(){
        List<CityDto> allCity = cityService.getAllCity();
        return new ResponseEntity<>(allCity,HttpStatus.OK);
    }

    @DeleteMapping("/deleteCity")
    public ResponseEntity<String> deleteCity(@RequestParam Long id){
        String s = cityService.deleteCity(id);
        return new ResponseEntity<>(s,HttpStatus.OK);
    }
}

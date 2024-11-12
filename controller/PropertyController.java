package com.hotelmansys.controller;

import com.hotelmansys.entity.City;
import com.hotelmansys.entity.Property;
import com.hotelmansys.payload.PropertyDto;
import com.hotelmansys.repository.CityRepository;
import com.hotelmansys.repository.PropertyRepository;
import com.hotelmansys.service.PropertyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/properties")
public class PropertyController {

  private PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;

    }

    @PostMapping
    public ResponseEntity<?> createProperty(@Valid @RequestBody PropertyDto propertyDto,
                                                      BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        PropertyDto propDto = propertyService.saveProperty(propertyDto);

        return new ResponseEntity<>(propDto, HttpStatus.CREATED);

    }

    //api/v1/properties/deleteProperty?id=2
    @DeleteMapping("/deleteProperty")
    public ResponseEntity<String> deleteProperty(@RequestParam Long id){

        propertyService.deleteProperty(id);

        return new ResponseEntity<>("Property deleted successfully", HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<PropertyDto>> getAllProperties(){
        List<PropertyDto> all = propertyService.getAll();

        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @PutMapping("/updateProperty")
    public ResponseEntity<Object> updateProperty(@Valid @RequestBody PropertyDto propertyDto
    ,@RequestParam Long id, BindingResult result){

        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        PropertyDto propertyDto1 = propertyService.updateProperty(propertyDto, id);

        return new ResponseEntity<>(propertyDto1, HttpStatus.OK);

    }
    @GetMapping("/search-hotels")
    public ResponseEntity<List<PropertyDto>> searchHotels(
            @RequestParam String name
    ){
        List<PropertyDto> result =propertyService.searchHotels(name);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }



}

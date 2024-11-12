package com.hotelmansys.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class PropertyDto {

    private String name;


    private Integer no_of_guest;


    private Integer no_of_bedrooms;


    private Integer no_of_beds;


    private Integer no_of_bathrooms;


    private String countryName;


    private String cityName;
}

package com.hotelmansys.repository;

import com.hotelmansys.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {

    @Query("Select c from City c where c.name=:name")
    Optional<City> findCityByName(@Param("name")String cityName);

}
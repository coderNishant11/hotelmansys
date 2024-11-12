package com.hotelmansys.repository;

import com.hotelmansys.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Long> {
    @Query("Select c from Country c where c.name=:name")
    Optional<Country> findCountryBYName(@Param("name") String countryName);

    //Optional<Country>findByName(String name)
}
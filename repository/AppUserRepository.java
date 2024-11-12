package com.hotelmansys.repository;

import com.hotelmansys.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {




   Optional<AppUser>findByUsername(String username);

   Optional<AppUser>findByEmail(String email);


   @Query("select a from AppUser a where a.username=:x or a.email=:x")
   Optional<AppUser>findByEmailOrUsername(@Param("x") String username);
}
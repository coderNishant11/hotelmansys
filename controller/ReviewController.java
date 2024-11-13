package com.hotelmansys.controller;

import com.hotelmansys.entity.AppUser;
import com.hotelmansys.entity.Review;
import com.hotelmansys.payload.ReviewDto;
import com.hotelmansys.repository.PropertyRepository;
import com.hotelmansys.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {

    private ReviewService reviewService;



    public ReviewController(ReviewService reviewService, PropertyRepository propertyRepository) {
        this.reviewService = reviewService;

    }


    //http://localhost:8080/api/v1/review?propertyId=1
    @PostMapping
    public ResponseEntity<Review> writeReview(
            @RequestBody ReviewDto reviewDto,
            @RequestParam long propertyId,
            @AuthenticationPrincipal AppUser user
            ){
        Review review=reviewService.writeReview(reviewDto,propertyId,user);


        return new ResponseEntity<>(review, HttpStatus.CREATED);


    }

}

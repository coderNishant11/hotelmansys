package com.hotelmansys.service;

import com.hotelmansys.entity.AppUser;
import com.hotelmansys.entity.Property;
import com.hotelmansys.entity.Review;
import com.hotelmansys.exception.ResourceNotFound;
import com.hotelmansys.payload.ReviewDto;
import com.hotelmansys.repository.PropertyRepository;
import com.hotelmansys.repository.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private PropertyRepository propertyRepository;
    private ReviewRepository reviewRepository;

    public ReviewService(PropertyRepository propertyRepository, ReviewRepository reviewRepository) {
        this.propertyRepository = propertyRepository;
        this.reviewRepository = reviewRepository;
    }

    public Review writeReview(ReviewDto reviewDto, long propertyId, AppUser user) {

        Property property = propertyRepository.findById(propertyId).orElseThrow(
                () -> new ResourceNotFound("Please provide valid property id")
        );
        Review review = new Review();
        review.setRating(reviewDto.getRating());
        review.setDescription(reviewDto.getDescription());
        review.setProperty(property);
        review.setAppUser(user);

        Review save = reviewRepository.save(review);

        return save;

    }
}

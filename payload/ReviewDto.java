package com.hotelmansys.payload;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data

public class ReviewDto {
    @NotNull(message = "")
    @Size(min =1, max=5, message = "rating should be in between 1 to 5")
    private Integer rating;

    @Size(min=10, max=1000, message = "Description should be in between 10 to 1000")
    private String description;
}

package com.hotelmansys.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDto {
    @NotNull
    @Size(min = 8, max = 25, message = "username should be in between 8 and 25")
    private String username;
    @NotNull
    @Size(min=4, max = 25, message = "name should be in between 4 and 25")
    private String name;
    @NotNull
    @Email
    private String email;

    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=])[A-Za-z\\d@#$%^&+=]{8,}$",
            message = "password must contain at least one uppercase letter, one lowercase letter, one number, and one special character from @#$%^&+=, and be at least 8 characters long"
    )
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String role;
}

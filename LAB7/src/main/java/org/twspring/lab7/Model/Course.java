package org.twspring.lab7.Model;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
@AllArgsConstructor
public class Course {

    @NotNull(message = "ID cannot be empty")
    @Positive(message = "ID cannot be negative or zero")
    @Min(value=10, message = "ID cannot be less than 10")
    private int id;

    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 4, max = 25 , message = "Name must have between 4 to 25 characters")
    private String name;

    @NotNull(message = "Duration cannot be empty")
    @Positive(message = "Duration cannot be negative or zero")
    @Range(min = 2, max = 24, message = "Duration must be between 2 to 24 weeks")
    private int duration_in_weeks;
}

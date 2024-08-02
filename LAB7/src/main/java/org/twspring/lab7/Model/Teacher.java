package org.twspring.lab7.Model;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Teacher {

    @NotNull(message = "ID cannot be empty")
    @Positive(message = "ID cannot be negative or zero")
    @Min(value=10, message = "ID cannot be less than 10")
    private int id;

    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 3, max = 25 , message = "Name must have between 3 to 25 characters")
    private String name;

    @NotEmpty(message = "Title cannot be empty")
    @Pattern(regexp = "^(Professor|Assistant Professor|Lecturer|Demonstrator)$", message = "Invalid title, only accepted titles are: Professor, Assistant Professor, Lecturer, Demonstrator")
    private String title;
}

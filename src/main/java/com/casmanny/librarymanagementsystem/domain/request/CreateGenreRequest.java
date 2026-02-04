package com.casmanny.librarymanagementsystem.domain.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateGenreRequest {
    @NotNull(message = "Code is required")
    private String code;
    @NotNull(message = "Name is required")
    @NotBlank(message = "Name must not be blank")
    private String name;
    @Size(max = 500, min = 10, message = "description must be between 10 and 500")
    private String description;
}

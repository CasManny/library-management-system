package com.casmanny.librarymanagementsystem.payload.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class GenreDTO {
    private Long id;
    @NotNull(message = "Code is required")
    private String code;
    @NotNull(message = "Name is required")
    @NotBlank(message = "Name must not be blank")
    private String name;
    @Size(max = 500, min = 10, message = "description must be between 10 and 500")
    private String description;
    private Integer displayOrder;
    private Boolean active;
    private Long parentGenreId;
    private String parentGenreName;
    private List<GenreDTO> subGenre;
    private Long bookCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

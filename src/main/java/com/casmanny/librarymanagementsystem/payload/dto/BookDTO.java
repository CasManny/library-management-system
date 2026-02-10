package com.casmanny.librarymanagementsystem.payload.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private Long id;
    @NotBlank(message = "ISBN is mandatory")
    private String isbn;

    @NotBlank(message = "Title is mandatory")
    @Size(min = 1, max = 256, message = "Title must be between 1 and 255 characters")
    private String title;

    @NotBlank(message = "Author is mandatory")
    @Size(min = 1, max = 255, message = "Author must be between 1 and 255 characters")
    private String author;

    @NotNull(message = "Genre is mandatory")
    private Long genreId;

    private String genreName;
    private String genreCode;

    @Size(max =100, message = "Publisher name must not exceed 100 characters")
    private String publisher;

    private LocalDate publicationDate;

    @Size(max = 20, message = "language must not exceed 20 characters")
    private String language;

    @Min(value = 1, message = "Pages must be at least 1")
    @Max(value = 50000, message = "Pages must not exceed 5000")
    private Integer pages;

    @Size(max = 2000, message = "Description must not exceed 2000 characters")
    private String description;

    @NotNull(message = "Total copies is mandatory")
    @Min(value = 0, message = "total copies cannot be negative")
    private Integer totalCopies;

    @NotNull(message = "Available copies is mandatory")
    @Min(value = 0, message = "Available copies must not be negative")
    private Integer availableCopies;

    @DecimalMin(value = "0.0", message = "Price cannot be negative")
    private BigDecimal price;

    private String coverImageUrl;

    private Boolean alreadyHaveLoan;
    private Boolean alreadyHaveReservation;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}

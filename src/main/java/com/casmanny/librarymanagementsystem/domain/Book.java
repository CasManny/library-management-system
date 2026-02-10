package com.casmanny.librarymanagementsystem.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String isbn;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private String author;

    private String publisher;
    private LocalDate publishedDate;
    private String language;
    private Integer pages;
    private String description;

    @Column(nullable = false)
    private Integer totalCopies;

    private Integer availableCopies;

    private BigDecimal price;
    private String coverImageUrl;

    @Column(nullable = false)
    private Boolean active = true;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;

    @AssertTrue(message = "Available copies cannot exceed total copies")
    public boolean isAvailableCopiesValid() {
        if(totalCopies == null || availableCopies == null) {
            return true;
        }
        return availableCopies <= totalCopies;
    }
}

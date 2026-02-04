package com.casmanny.librarymanagementsystem.repository;

import com.casmanny.librarymanagementsystem.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}

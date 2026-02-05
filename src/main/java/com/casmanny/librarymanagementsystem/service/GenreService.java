package com.casmanny.librarymanagementsystem.service;

import com.casmanny.librarymanagementsystem.payload.dto.GenreDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GenreService {
    GenreDTO createGenre(GenreDTO request);
    List<GenreDTO> getAllGenres();
    GenreDTO getGenreById(Long genreId);
    GenreDTO updateGenre(Long genreId, GenreDTO genre);
    void toggleGenreStatus(Long genreId);
    void hardDeleteGenre(Long genreId);
    List<GenreDTO> getAllActiveGenresWithSubGenres();
    List<GenreDTO> getTopLevelGenres();
    Page<GenreDTO> searchGenres(String searchTerm, Pageable pageable);
    long getTotalActiveGenre();
    long getBookCountByGenre(Long genreId);

}

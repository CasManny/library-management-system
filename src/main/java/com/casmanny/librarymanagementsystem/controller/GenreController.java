package com.casmanny.librarymanagementsystem.controller;

import com.casmanny.librarymanagementsystem.payload.dto.GenreDTO;
import com.casmanny.librarymanagementsystem.payload.response.ApiResponse;
import com.casmanny.librarymanagementsystem.service.GenreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/genres")
public class GenreController {
    private final GenreService genreService;

    @PostMapping
    public ResponseEntity<GenreDTO> addGenre(@RequestBody @Valid GenreDTO request) {
        GenreDTO newGenre = genreService.createGenre(request);
        return ResponseEntity.status(CREATED).body(newGenre);
    }

    @GetMapping
    public ResponseEntity<List<GenreDTO>> getAllGenres() {
        return ResponseEntity.ok(genreService.getAllGenres());
    }

    @GetMapping(path = "/{genre-id}")
    public ResponseEntity<GenreDTO> getGenreById(@PathVariable("genre-id") Long genreId) {
        return ResponseEntity.ok(genreService.getGenreById(genreId));
    }

    @PutMapping(path = "/{genre-id}")
    public ResponseEntity<GenreDTO> updateGenre(@RequestBody @Valid GenreDTO request, @PathVariable("genre-id") Long genreId) {
        return ResponseEntity.ok(genreService.updateGenre(genreId, request));

    }

    @PostMapping(path = "/{genre-id}")
    public ResponseEntity<ApiResponse> deactivateGenre(@PathVariable("genre-id") Long genreId) {
        genreService.toggleGenreStatus(genreId);
        ApiResponse response = ApiResponse.builder()
                .message("Genre deactivated successfully")
                .status(true)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/top-level")
    public ResponseEntity<List<GenreDTO>> getTopLevelGenres() {
        return ResponseEntity.ok(genreService.getTopLevelGenres());
    }

    @GetMapping(path = "/count")
    public ResponseEntity<Long> getTotalActiveGenres() {
        return ResponseEntity.ok(genreService.getTotalActiveGenre());
    }

    @GetMapping(path = "/{genre-id}/book-count")
    public ResponseEntity<Long> getBookCountByGenres(@PathVariable("genre-id") Long genreId) {
        return ResponseEntity.ok(genreService.getBookCountByGenre(genreId));
    }

    @DeleteMapping(path = "/{genre-id}")
    public ResponseEntity<ApiResponse> deleteGenre(@PathVariable("genre-id") Long genreId) {
        genreService.hardDeleteGenre(genreId);
        ApiResponse response = ApiResponse.builder()
                .message("Genre deleted successfully")
                .status(true)
                .build();
        return ResponseEntity.ok(response);
    }


}

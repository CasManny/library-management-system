package com.casmanny.librarymanagementsystem.controller;

import com.casmanny.librarymanagementsystem.domain.request.CreateGenreRequest;
import com.casmanny.librarymanagementsystem.domain.response.GenreResponse;
import com.casmanny.librarymanagementsystem.service.GenreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/genres")
public class GenreController {
    private final GenreService genreService;

    @PostMapping
    public ResponseEntity<GenreResponse> addGenre(@RequestBody @Valid CreateGenreRequest request) {
        GenreResponse newGenre = genreService.createGenre(request);
        return ResponseEntity.status(CREATED).body(newGenre);
    }
}

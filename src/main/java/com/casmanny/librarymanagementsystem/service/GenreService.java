package com.casmanny.librarymanagementsystem.service;

import com.casmanny.librarymanagementsystem.domain.Genre;
import com.casmanny.librarymanagementsystem.domain.request.CreateGenreRequest;
import com.casmanny.librarymanagementsystem.domain.response.GenreResponse;

public interface GenreService {
    GenreResponse createGenre(CreateGenreRequest request);
}

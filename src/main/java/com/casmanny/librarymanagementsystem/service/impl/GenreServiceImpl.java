package com.casmanny.librarymanagementsystem.service.impl;

import com.casmanny.librarymanagementsystem.domain.Genre;
import com.casmanny.librarymanagementsystem.domain.request.CreateGenreRequest;
import com.casmanny.librarymanagementsystem.domain.response.GenreResponse;
import com.casmanny.librarymanagementsystem.mapper.GenreMapper;
import com.casmanny.librarymanagementsystem.repository.GenreRepository;
import com.casmanny.librarymanagementsystem.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    @Override
    public GenreResponse createGenre(CreateGenreRequest request) {
        Genre genre = genreMapper.toGenre(request);
        return genreMapper.toGenreResponse(genre);
    }
}

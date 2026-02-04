package com.casmanny.librarymanagementsystem.mapper;

import com.casmanny.librarymanagementsystem.domain.Genre;
import com.casmanny.librarymanagementsystem.domain.request.CreateGenreRequest;
import com.casmanny.librarymanagementsystem.domain.response.GenreResponse;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper {
    public Genre toGenre(CreateGenreRequest request) {
        return Genre.builder()
                .code(request.getCode())
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public GenreResponse toGenreResponse(Genre genre) {
        return GenreResponse.builder()
                .id(genre.getId())
                .code(genre.getCode())
                .name(genre.getName())
                .description(genre.getDescription())
                .displayOrder(genre.getDisplayOrder())
                .active(genre.getActive())
                .build();
    }
}

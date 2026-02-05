package com.casmanny.librarymanagementsystem.mapper;

import com.casmanny.librarymanagementsystem.domain.Genre;
import com.casmanny.librarymanagementsystem.payload.dto.GenreDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GenreMapper {

    public Genre toGenre(GenreDTO request) {
        return Genre.builder()
                .code(request.getCode())
                .name(request.getName())
                .description(request.getDescription())
                .displayOrder(request.getDisplayOrder())
                .active(true)
                .build();
    }

    public GenreDTO toGenreDTO(Genre genre) {
        GenreDTO genreDTO =  GenreDTO.builder()
                .id(genre.getId())
                .code(genre.getCode())
                .description(genre.getDescription())
                .active(genre.getActive())
                .displayOrder(genre.getDisplayOrder())
                .createdAt(genre.getCreatedAt())
                .updatedAt(genre.getUpdatedAt())
                .build();

        if(genre.getParentGenre() != null) {
            genreDTO.setParentGenreId(genre.getParentGenre().getId());
            genreDTO.setParentGenreName(genre.getParentGenre().getName());
        }

        if(genre.getSubGenres() != null && !genre.getSubGenres().isEmpty()) {
            List<GenreDTO> subGenreDTOs = genre.getSubGenres().stream()
                    .filter(Genre::getActive)
                    .map(subGenre ->
                            GenreDTO.builder()
                                    .id(subGenre.getId())
                                    .code(subGenre.getCode())
                                    .name(subGenre.getName())
                                    .build()
                    )
                    .toList();

            genreDTO.setSubGenre(subGenreDTOs);
        }

       genreDTO.setBookCount((long) genre.getBooks().size());

        return genreDTO;
    }


    public Genre updateEntityFromDto(GenreDTO dto, Genre genre) {
        if(dto == null || genre == null) {
            return null;
        }
        genre.setCode(dto.getCode());
        genre.setName(dto.getName());
        genre.setDescription(dto.getDescription());
        genre.setDisplayOrder(dto.getDisplayOrder() != null ? dto.getDisplayOrder() : 0);

        if(dto.getActive() != null) {
            genre.setActive(dto.getActive());
        }
        return genre;
    }
}

package com.casmanny.librarymanagementsystem.service.impl;

import com.casmanny.librarymanagementsystem.domain.Genre;
import com.casmanny.librarymanagementsystem.exception.GenreNotFoundException;
import com.casmanny.librarymanagementsystem.mapper.GenreMapper;
import com.casmanny.librarymanagementsystem.payload.dto.GenreDTO;
import com.casmanny.librarymanagementsystem.repository.GenreRepository;
import com.casmanny.librarymanagementsystem.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;


    @Override
    public GenreDTO createGenre(GenreDTO request) {
        Genre genre = genreMapper.toGenre(request);
        if(request.getParentGenreId() != null) {
            Genre parentGenre = genreRepository.findById(request.getParentGenreId())
                    .orElseThrow(() -> new GenreNotFoundException("Parent Genre not found"));
            genre.setParentGenre(parentGenre);
        }
        Genre savedGenre = genreRepository.save(genre);
        return genreMapper.toGenreDTO(savedGenre);
    }

    @Override
    public List<GenreDTO> getAllGenres() {
        List<Genre> genres = genreRepository.findAll();
        return genres.stream()
                .map(genreMapper::toGenreDTO)
                .toList();
    }

    @Override
    public GenreDTO getGenreById(Long genreId) {
        Genre genre = getGenreOrThrowNotFoundException(genreId);
        return genreMapper.toGenreDTO(genre);
    }

    @Override
    public GenreDTO updateGenre(Long genreId, GenreDTO genreDTO) {
        Genre existingGenre = getGenreOrThrowNotFoundException(genreId);
        Genre updatedGenre = genreMapper.updateEntityFromDto(genreDTO, existingGenre);

        if(genreDTO.getParentGenreId() != null) {
            Genre parentGenre = genreRepository.findById(genreDTO.getParentGenreId())
                    .orElseThrow(() -> new GenreNotFoundException("No parent genre found "));
            updatedGenre.setParentGenre(parentGenre);
        }

        Genre savedGenre = genreRepository.save(updatedGenre);
        return genreMapper.toGenreDTO(savedGenre);
    }



    @Override
    public void toggleGenreStatus(Long genreId) {
        Genre genre = getGenreOrThrowNotFoundException(genreId);
        genre.setActive(!genre.getActive());
        genreRepository.save(genre);
    }

    @Override
    public void hardDeleteGenre(Long genreId) {
        genreRepository.deleteById(genreId);
    }

    @Override
    public List<GenreDTO> getAllActiveGenresWithSubGenres() {
        List<Genre> topLevelGenres = genreRepository.findByParentGenreIsNullActiveTrueOrderByDisplayOrderAsc();
        return topLevelGenres.stream()
                .map(genreMapper::toGenreDTO)
                .toList();
    }

    @Override
    public List<GenreDTO> getTopLevelGenres() {
        return List.of();
    }

    @Override
    public Page<GenreDTO> searchGenres(String searchTerm, Pageable pageable) {
        return null;
    }

    @Override
    public long getTotalActiveGenre() {
        return genreRepository.countByActiveTrue();
    }

    @Override
    public long getBookCountByGenre(Long genreId) {
        return genreRepository.countBooksByGenre(genreId);
    }

    private Genre getGenreOrThrowNotFoundException(Long genreId) {
        return genreRepository.findById(genreId)
                .orElseThrow(() -> new GenreNotFoundException("No genre found"));
    }
}

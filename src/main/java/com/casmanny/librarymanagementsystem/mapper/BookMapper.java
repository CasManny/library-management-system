package com.casmanny.librarymanagementsystem.mapper;

import com.casmanny.librarymanagementsystem.domain.Book;
import com.casmanny.librarymanagementsystem.payload.dto.BookDTO;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    public BookDTO toBookDTO(Book book) {
        return BookDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .genreName(book.getGenre().getName())
                .genreCode(book.getGenre().getCode())
                .genreId(book.getGenre().getId())
                .isbn(book.getIsbn())
                .publisher(book.getPublisher())
                .publicationDate(book.getPublishedDate())
                .language(book.getLanguage())
                .pages(book.getPages())
                .description(book.getDescription())
                .totalCopies(book.getTotalCopies())
                .availableCopies(book.getAvailableCopies())
                .price(book.getPrice())
                .coverImageUrl(book.getCoverImageUrl())
                .active(book.getActive())
                .createdAt(book.getCreatedAt())
                .updatedAt(book.getUpdatedAt())
                .build();
    }
    public Book toBookEntity(BookDTO dto) {
        return Book.builder()
                .isbn(dto.getIsbn())
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .publishedDate(dto.getPublicationDate())
                .publisher(dto.getPublisher())
                .language(dto.getLanguage())
                .pages(dto.getPages())
                .description(dto.getDescription())
                .totalCopies(dto.getTotalCopies())
                .availableCopies(dto.getAvailableCopies())
                .price(dto.getPrice())
                .coverImageUrl(dto.getCoverImageUrl())
                .active(true)
                .build();
    }

    public void updateEntityFromDTO(Book existingBook, BookDTO dto) {
        existingBook.setTitle(dto.getTitle());
        existingBook.setAuthor(dto.getAuthor());
        existingBook.setPublishedDate(dto.getPublicationDate());
        existingBook.setPublisher(dto.getPublisher());
        existingBook.setLanguage(dto.getLanguage());
        existingBook.setPages(dto.getPages());
        existingBook.setPrice(dto.getPrice());
        existingBook.setDescription(dto.getDescription());
        existingBook.setTotalCopies(dto.getTotalCopies());
        existingBook.setAvailableCopies(dto.getAvailableCopies());
        existingBook.setCoverImageUrl(dto.getCoverImageUrl());
        existingBook.setActive(dto.getActive() != null ? dto.getActive() : null);

    }
}

package com.casmanny.librarymanagementsystem.service;

import com.casmanny.librarymanagementsystem.domain.Book;
import com.casmanny.librarymanagementsystem.payload.dto.BookDTO;
import com.casmanny.librarymanagementsystem.payload.request.BookSearchRequest;
import com.casmanny.librarymanagementsystem.payload.response.PageResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookService {
    BookDTO createBook(BookDTO bookDTO);
    List<BookDTO> createBooksBulks(List<BookDTO> bookDTOS);
    BookDTO getBookById(Long bookId);
    BookDTO getBookByIsbn(String isbn);
    BookDTO updateBook(Long bookId, BookDTO bookDTO);
    void deactivateBook(Long bookId);
    void deleteBook(Long bookId);
    PageResponse<BookDTO> searchBookWithFilters(BookSearchRequest searchRequest);
    long getTotalActiveBooks();
    long getTotalAvailableBooks();
}

package com.casmanny.librarymanagementsystem.service.impl;

import com.casmanny.librarymanagementsystem.domain.Book;
import com.casmanny.librarymanagementsystem.domain.Genre;
import com.casmanny.librarymanagementsystem.exception.BookException;
import com.casmanny.librarymanagementsystem.exception.GenreNotFoundException;
import com.casmanny.librarymanagementsystem.mapper.BookMapper;
import com.casmanny.librarymanagementsystem.payload.dto.BookDTO;
import com.casmanny.librarymanagementsystem.payload.request.BookSearchRequest;
import com.casmanny.librarymanagementsystem.payload.response.PageResponse;
import com.casmanny.librarymanagementsystem.repository.BookRepository;
import com.casmanny.librarymanagementsystem.repository.GenreRepository;
import com.casmanny.librarymanagementsystem.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final BookMapper bookMapper;
    private static final int MIN_PAGE = 0;
    private static final int MIN_SIZE = 1;
    private static final int MAX_SIZE = 50;

    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        boolean existingIsbn = bookRepository.existsByIsbn(bookDTO.getIsbn());
        if(existingIsbn) {
            throw new BookException("Book with isbn already exist");
        }
        Genre genre = genreRepository.findById(bookDTO.getGenreId()).orElseThrow(() -> new GenreNotFoundException("Genre not found"));
        Book book = bookMapper.toBookEntity(bookDTO);
        book.setGenre(genre);

        if(!book.isAvailableCopiesValid()) {
            throw new BookException("Available copies must be less than total copies");
        }
        Book savedBook = bookRepository.save(book);

        return bookMapper.toBookDTO(savedBook);
    }

    @Override
    @Transactional
    public List<BookDTO> createBooksBulks(List<BookDTO> bookDTOS) {
        List<BookDTO> createdBooks = new ArrayList<>();
        for(BookDTO bookDTO: bookDTOS) {
            BookDTO book = createBook(bookDTO);
            createdBooks.add(book);
        }
        return createdBooks;
    }

    @Override
    public BookDTO getBookById(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookException("book not found"));
        return bookMapper.toBookDTO(book);
    }

    @Override
    public BookDTO getBookByIsbn(String isbn) {
        Book book = bookRepository.findByIsbn(isbn).orElseThrow(() -> new BookException("Book with isbn not found"));
        return bookMapper.toBookDTO(book);
    }

    @Override
    public BookDTO updateBook(Long bookId, BookDTO bookDTO) {
        Book existingBook = bookRepository.findById(bookId).orElseThrow(() -> new BookException("Book not found"));
        bookMapper.updateEntityFromDTO(existingBook, bookDTO);
        existingBook.isAvailableCopiesValid();
        Genre genre = genreRepository.findById(bookDTO.getGenreId())
                .orElseThrow(() -> new GenreNotFoundException("Genre not found"));

        existingBook.setGenre(genre);

        Book updatedBook = bookRepository.save(existingBook);
        return bookMapper.toBookDTO(updatedBook);
    }

    @Override
    @Transactional
    public void deactivateBook(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookException("Book not found"));
        book.setActive(false);
    }

    @Override
    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    @Override
    public PageResponse<BookDTO> searchBookWithFilters(BookSearchRequest request) {
        int page = request.getPage() == null || request.getPage() < MIN_PAGE
                ? MIN_PAGE
                : request.getPage();

        int size = request.getSize() == null
                ? 20
                : Math.min(Math.max(request.getSize(), MIN_SIZE), MAX_SIZE);

        Sort sort = Sort.by(
                Sort.Direction.fromString(request.getSortDirection()),
                request.getSortBy()
        );

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Book> books = bookRepository.searchBooksWithFilters(
                request.getSearchTerm(),
                request.getGenreId(),
                request.getAvailableOnly(),
                pageable

        );

        return convertToPageResponse(books);

    }

    @Override
    public long getTotalActiveBooks() {
        return bookRepository.countByActiveTrue();
    }

    @Override
    public long getTotalAvailableBooks() {
        return bookRepository.countAvailableBooks();
    }

    private PageResponse<BookDTO> convertToPageResponse(Page<Book> books) {
        List<BookDTO> bookDTOS = books.getContent().stream()
                .map(bookMapper::toBookDTO)
                .toList();
        return new PageResponse<>(
                bookDTOS,
                books.getNumber(),
                books.getSize(),
                books.getTotalElements(),
                books.getTotalPages(),
                books.isLast(),
                books.isFirst(),
                books.isEmpty()
        );
    }
}

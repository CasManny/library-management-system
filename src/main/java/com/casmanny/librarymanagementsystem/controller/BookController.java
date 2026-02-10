package com.casmanny.librarymanagementsystem.controller;

import com.casmanny.librarymanagementsystem.payload.dto.BookDTO;
import com.casmanny.librarymanagementsystem.payload.request.BookSearchRequest;
import com.casmanny.librarymanagementsystem.payload.response.BookStatsResponse;
import com.casmanny.librarymanagementsystem.payload.response.PageResponse;
import com.casmanny.librarymanagementsystem.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody @Valid BookDTO request) {
        return ResponseEntity.ok(bookService.createBook(request));
    }

    @PostMapping(path = "/bulk")
    public ResponseEntity<List<BookDTO>> createBulkBooks(@RequestBody @Valid List<BookDTO> request) {
        return ResponseEntity.ok(bookService.createBooksBulks(request));
    }

    @GetMapping(path = "/{book-id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable("book-id") Long bookId){
        return ResponseEntity.ok(bookService.getBookById(bookId));
    }

    @PutMapping(path = "/{book-id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable("book-id") Long bookId, @RequestBody @Valid BookDTO request) {
        return ResponseEntity.ok(bookService.updateBook(bookId, request));
    }

    @PatchMapping(path = "/{book-id}/deactivate")
    public ResponseEntity<Void> deactivateBook(@PathVariable("book-id") Long bookId) {
        bookService.deactivateBook(bookId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/{book-id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("book-id") Long bookId) {
        bookService.deleteBook(bookId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "/search")
    public ResponseEntity<PageResponse<BookDTO>> searchBook(@RequestBody BookSearchRequest request) {
        return ResponseEntity.ok(bookService.searchBookWithFilters(request));
    }

    @GetMapping(path = "/stats")
    public ResponseEntity<BookStatsResponse> getBookStats() {
        long totalActive = bookService.getTotalActiveBooks();
        long totalAvailable = bookService.getTotalAvailableBooks();
        BookStatsResponse response = BookStatsResponse.builder()
                .totalActive(totalActive)
                .totalAvailable(totalAvailable)
                .build();

        return ResponseEntity.ok(response);
    }

}

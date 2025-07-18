package com.example.book.controller;

import com.example.book.exception.ResourceNotFoundException;
import com.example.book.model.Book;
import com.example.book.repository.BookRepository;
import com.example.book.service.BookService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    //Create new book
    @PostMapping
    public Book createBook(@RequestBody Book book){
        return bookService.createBook(book);
    }

    //get Book by Id
//    @GetMapping("/{id}")
//    public Optional<Book> getBookById(@PathVariable Long id){
//        return bookRepository.findById(id);
//    }

      // 🔁 Without Lambda: Optional.orElseThrow with Anonymous Class
//    @GetMapping("/{id}")
//    public Book getBookById(@PathVariable Long id) {
//        return bookRepository.findById(id).orElseThrow(new Supplier<RuntimeException>() {
//            @Override
//            public RuntimeException get() {
//                return new ResourceNotFoundException("Book not found with ID: " + id);
//            }
//        });
//    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id){
        Book v = bookService.getBookById(id);
        return v;
    }

//    @GetMapping("/{id}")
//    public Book getBookById(@PathVariable Long id){
//        var v = bookService.getBookById(id);
//        return v;
//    }

    //Get all books
    @GetMapping
    public List<Book> getAllBook(){
        return bookService.getAllBooks();
    }

    //Update the book Details
    @PutMapping("/{id}")
    public Optional<Book> updateBookById(@PathVariable Long id, @RequestBody Book book){
        return bookService.updateBookById(id, book);
    }

    // Delete the book by ID
    @DeleteMapping("/{id}")
    public String deleteBookById(@PathVariable Long id){
        return bookService.deleteBookById(id);
    }
}

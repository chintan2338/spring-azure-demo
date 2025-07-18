package com.example.book.service;

import com.example.book.exception.ResourceNotFoundException;
import com.example.book.model.Book;
import com.example.book.repository.BookRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public Book createBook(Book book){
        return bookRepository.save(book);
    }

//    @Cacheable(value = "books", key = "#id")
//    public Book getBookById(Long id){
//        System.out.println("Fetching book from DB (not cache): " + id);
//        return bookRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Book not found with ID: " + id));
//    }

//    @Cacheable(value = "books", key = "#id")
    public Book getBookById(Long id) {
        try {
            System.out.println("Fetching book from DB (not cache): " + id);
            return bookRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Book not found with ID: " + id));
        } catch (Exception e) {

            System.err.println("Error while fetching book: " + e.getMessage());
            throw e;
        }
    }


//    @Cacheable(value = "allBooks")
    public List<Book> getAllBooks(){
        System.out.println("Fetching ALL books from DB (not cache)");
        return bookRepository.findAll();
    }

//    @CacheEvict(value = {"books", "allBooks"}, key = "#id")
    public Optional<Book> updateBookById(Long id, Book book){
        System.out.println("Updating Book Successfully");
        Optional<Book> oldBook = bookRepository.findById(id);
        if(oldBook.isPresent()){
            Book updated = oldBook.get();
            updated.setAuthor(book.getAuthor());
            updated.setTitle(book.getTitle());
            updated.setPrice(book.getPrice());
            bookRepository.save(updated);
            return Optional.of(updated);
        }
        return Optional.empty();
    }

//    @CacheEvict(value = {"books", "allBooks"}, key = "#id")
    public String deleteBookById(Long id){
        Optional<Book> book = bookRepository.findById(id);
        if(book.isPresent()){
            bookRepository.deleteById(id);
            return "Book with ID " + id + " has been deleted.";
        }
        return "Book not found with ID: " + id;
    }
}

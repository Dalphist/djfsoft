package service;

import java.util.List;

import pojo.Book;


public interface BookService {
	List<Book> getAllBooksList();
	
	List<Book> getBooksListByType(String type);
	
	void addBook(Book book);
	void delBook(String book_id);
}

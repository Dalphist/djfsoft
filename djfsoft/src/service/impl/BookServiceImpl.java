package service.impl;

import java.util.List;

import mapper.BookMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pojo.Book;

import service.BookService;


@Service
public class BookServiceImpl implements BookService{
	@Autowired
	BookMapper bookMapper;

	public List<Book> getAllBooksList() {
		return bookMapper.getAllBookslist();
	}

	public List<Book> getBooksListByType(String type) {
		return bookMapper.getBookslistByType(type);
	}

	public void addBook(Book book) {
		bookMapper.addBook(book);
		return;
	}

	public void delBook(String book_id) {
		bookMapper.delBook(book_id);
	}

}

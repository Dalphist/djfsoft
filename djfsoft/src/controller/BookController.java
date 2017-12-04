package controller;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import pojo.Book;

import service.BookService;

@Controller
@RequestMapping("book")
public class BookController {
	@Autowired
	BookService bookService;

	@RequestMapping("getAllBooksList")
	@ResponseBody
	public List<Book> getTypeList() {
		List<Book> list = bookService.getAllBooksList();
		return list;
	}
	
	@RequestMapping("addSave")
	@ResponseBody
	public void addSave(String book) {
		JSONObject obj = JSONObject.fromObject(book);
		Book b = (Book) JSONObject.toBean(obj, Book.class);
		bookService.addBook(b);
		return;
	}
	
	@RequestMapping("del")
	@ResponseBody
	public void del(String book_id) {
		bookService.delBook(book_id);
		return;
	}
}

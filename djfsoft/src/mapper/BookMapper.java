package mapper;

import java.util.List;

import pojo.Book;

public interface BookMapper {
    public List<Book> getAllBookslist();
    
    public List<Book> getBookslistByType(String type_id);
    
    public void addBook(Book book);
    
    public void delBook(String book_id);
}

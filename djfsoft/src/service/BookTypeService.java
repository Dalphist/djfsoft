package service;

import java.util.List;

import pojo.BookType;


public interface BookTypeService {
	List<BookType> getRootTypesList();
	List<BookType> getChildrenTypesList(String parent_id);
}

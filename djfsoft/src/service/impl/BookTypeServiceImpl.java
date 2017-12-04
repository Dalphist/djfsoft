package service.impl;

import java.util.List;

import mapper.BookTypeMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pojo.BookType;

import service.BookTypeService;


@Service
public class BookTypeServiceImpl implements BookTypeService{
	@Autowired
	BookTypeMapper bookTypeMapper;

	public List<BookType> getRootTypesList() {
		return bookTypeMapper.getRootTypesList();
	}

	public List<BookType> getChildrenTypesList(String parent_id) {
		return bookTypeMapper.getChildrenTypesList(parent_id);
	}

}

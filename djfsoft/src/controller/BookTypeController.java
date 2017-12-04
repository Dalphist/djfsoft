package controller;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;

import pojo.BookType;

import service.BookTypeService;

@Controller
@RequestMapping("")
public class BookTypeController {
	@Autowired
	BookTypeService bookTypeService;

	@RequestMapping("getRootTypesList")
	@ResponseBody
	public JSONArray getTypeList() {
		List<BookType> list = bookTypeService.getRootTypesList();
		JSONArray arr = new JSONArray();
		for (BookType type : list) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", type.getType_id());
			jsonObject.put("text", type.getType_name());
			jsonObject.put("state", "closed");
			arr.add(jsonObject);
		}
		return arr;
	}
	
	@RequestMapping("getChildrenTypesList")
	@ResponseBody
	public JSONArray getChildrenTypesList(String parent_id) {
		List<BookType> list = bookTypeService.getChildrenTypesList(parent_id);
		JSONArray arr = new JSONArray();
		for (BookType type : list) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", type.getType_id());
			jsonObject.put("text", type.getType_name());
			jsonObject.put("state", "open");
			arr.add(jsonObject);
		}
		return arr;
	}

}

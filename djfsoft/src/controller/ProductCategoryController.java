package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pojo.ProductCategory;
import pojo.ResultBean;
import pojo.Tree;
import pojo.User;
import service.ProductCategoryService;
import service.UserService;
import util.BeanUtil;
import util.DateUtil;

@Controller
@RequestMapping("productCategory")
public class ProductCategoryController {
	@Autowired
	UserService userService;
	
	@Autowired
	ProductCategoryService productCategoryService;
	
	@RequestMapping("getItemCategories")
	@ResponseBody
	public Map<String, String> getUser(String user_name, String password, HttpSession session) {
		User user = userService.getUser(new User(user_name, password));
		Map<String, String> map = new HashMap<String, String>();
		String result = (user == null ? "0" : "1");
		session.setAttribute("user", user);
		// Log.login(user);
		map.put("result", result);
		return map;
	}
	
	@RequestMapping("addProductCategory")
	@ResponseBody
	public ResultBean<String> addProductCategory(String categoryInfo) {
		ProductCategory pc = (ProductCategory) BeanUtil.getBeanFromStr(categoryInfo, "pojo.ProductCategory");
		pc.setGmtCreate(DateUtil.getNowDate());
		productCategoryService.addProductCategory(pc);
		ResultBean<String> result = new ResultBean<String>();
		result.setCode(ResultBean.SUCCESS);
		result.setMsg("添加成功！");
		return result;
	}
	
	@RequestMapping("delProductCategory")
	@ResponseBody
	public ResultBean<String> delProductCategory(String categoryId) {
		productCategoryService.delProductCategory(categoryId);
		ResultBean<String> result = new ResultBean<String>();
		result.setCode(ResultBean.SUCCESS);
		result.setMsg("删除成功！");
		return result;
	}

	@RequestMapping("main")
	public String loginSuccess() {
		String url = "main";
		return url;
	}

	/**
	 * 获取商品分类树形
	 * 
	 * @return
	 */
	@RequestMapping("getProductCategoryTree")
	@ResponseBody
	public List<Tree> getTree() {
//		List<ProductCategory> rootList = productCategoryService.getProductCategoryByParentId(0); // 根节点
//		List<ProductCategory> list = buildTree(rootList);
		List<Tree> root = productCategoryService.getTreeByCategoryId(0); // 根节点
		List<Tree> list = buildTree(root);
		return list;
	}

	public List<Tree> buildTree(List<Tree> root) {
		for (Tree tree : root) {
			List<Tree> children = productCategoryService.getTreeByCategoryId(tree.getId());
			tree.setChildren(children);
			buildTree(children);
		}
		return root;
	}

}

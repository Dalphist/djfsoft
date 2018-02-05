package controller.manage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pojo.ResultBean;
import pojo.Tree;
import pojo.User;
import pojo.manage.ProductCategory;
import pojo.manage.ProductInfo;
import service.manage.ProductCategoryService;
import service.manage.ProductService;
import service.manage.UserService;
import util.DateUtil;
import util.ParseUtil;

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
	
	@RequestMapping("validateCategory")
	@ResponseBody
	public ResultBean<String> validateCategory(String categoryInfo) {
		ProductCategory productCategory = new ProductCategory();
		productCategory = (ProductCategory)ParseUtil.getBeanFromStr(categoryInfo, "pojo.manage.ProductCategory");
		ResultBean<String> result = new ResultBean<String>();
		int a = productCategoryService.getCategoryCountByInfo(productCategory);
		if (a > 0) {
			result.setCode(ResultBean.FAIL);
			result.setMsg("分类名称重复！");
		}
		return result;
	}
	
	@RequestMapping("saveProductCategory")
	@ResponseBody
	public ResultBean<String> saveProductCategory(String categoryInfo,String attributeIdStr) {
		//保存基本信息
		ProductCategory pc = (ProductCategory) ParseUtil.getBeanFromStr(categoryInfo, "pojo.manage.ProductCategory");
		if(pc.getId() == null){		//新加
			pc.setGmtCreate(DateUtil.getNowDate());
			productCategoryService.addProductCategory(pc);
		}else{		//修改
			pc.setGmtModified(DateUtil.getNowDate());
			productCategoryService.update(pc);
		}
		String categoryId = String.valueOf(pc.getId());
		//获取本分类及所有子分类
		List<ProductCategory> children = productCategoryService.getChildrenCategoryById(categoryId);
		//删除本分类及子分类所有规格信息
		delChildrenAttribute(categoryId,children);
		//保存规格信息
		if(attributeIdStr.length() > 0){
			saveChildrenAttribute(attributeIdStr, children);
		}
		ResultBean<String> result = new ResultBean<String>();
		result.setCode(ResultBean.SUCCESS);
		result.setMsg("保存成功！");
		return result;
	}

	private void saveChildrenAttribute(String attributeIdStr,List<ProductCategory> children) {
		String[] attributeIdArray = attributeIdStr.split(",");
		for (int i = 0; i < attributeIdArray.length; i++) {
		    String attributeId = attributeIdArray[i];
		    for(ProductCategory child :children){
		    	String id = child.getId().toString();
		    	productCategoryService.addCategoryToAttribute(id, attributeId);
		    }
		}
	}

	private void delChildrenAttribute(String categoryId,List<ProductCategory> children) {
		for(ProductCategory child : children){
			String id = child.getId().toString();
			productCategoryService.delCategoryToAttribute(id);
		}
	}
	
	@RequestMapping("getProductCategory")
	@ResponseBody
	public ResultBean<ProductCategory> getCategoryById(String categoryId) {
		ProductCategory productCategory = productCategoryService.getProductCategoryById(categoryId);
		ResultBean<ProductCategory> result = new ResultBean<ProductCategory>();
		result.setData(productCategory);
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
		List<Tree> root = productCategoryService.getTreeByCategoryId("0"); // 根节点
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
	
	@RequestMapping("getCategoryToProductTree")
	@ResponseBody
	public List<Tree> getCategoryToProductTree() {
		List<Tree> root = productCategoryService.getTreeByCategoryId("0"); // 根节点
		List<Tree> list = buildProductTree(root);
		return list;
	}
	
	@Autowired
	ProductService productService;
	
	public List<Tree> buildProductTree(List<Tree> root) {
		for (Tree tree : root) {
			List<Tree> children = productCategoryService.getTreeByCategoryId(tree.getId());
			if(children.size() > 0){
				tree.setChildren(children);
				buildProductTree(children);
			}else{
				List<ProductInfo> productInfoList = productService.getProductInfoByCategoryId(String.valueOf(tree.getId()));
				List<Tree> list = new ArrayList<Tree>();
				for(ProductInfo p : productInfoList){
					Tree t = new Tree();
					t.setId("p"+p.getId());
					t.setParentId(tree.getId());
					t.setBarCode(p.getBarCode());
					t.setProductCode(p.getProductCode());
					t.setText(p.getProductName());
					t.setStock(p.getStock());
					list.add(t);
				}
				tree.setChildren(list);
			}
		}
		return root;
	}

}

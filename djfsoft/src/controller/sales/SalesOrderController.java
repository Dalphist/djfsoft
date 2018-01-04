package controller.sales;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import pojo.Product;
import pojo.ResultBean;
import pojo.sales.SalesOrderDetailInfo;
import pojo.sales.SalesOrderInfo;
import service.sales.SalesOrderService;
import util.DateUtil;
import util.ParseUtil;


@Controller
@RequestMapping("sales/salesOrder")
public class SalesOrderController {
	@Autowired
	SalesOrderService SalesOrderService;

	@RequestMapping("index")
	public ModelAndView productIndex() {
		ModelAndView mav = new ModelAndView();
		String url = "sales/salesOrder/index";
		mav.setViewName(url);
		return mav;
	}
	
	@RequestMapping("orderList")
	public ModelAndView orderList() {
		List<SalesOrderInfo> list = SalesOrderService.orderList();
		ModelAndView mav = new ModelAndView();
		String url = "sales/salesOrder/orderList";
		mav.setViewName(url);
		mav.addObject("orderList", list);
		return mav;
	}
	
	@RequestMapping("orderDetail")
	public ModelAndView orderDetail(String orderId) {
		List<SalesOrderDetailInfo> list = SalesOrderService.getDetail(orderId);
		ModelAndView mav = new ModelAndView();
		String url = "sales/salesOrder/orderDetail";
		mav.setViewName(url);
		mav.addObject("detailList", list);
		return mav;
	}
	
	@RequestMapping("saveSalesOrder")
	@ResponseBody
	public ResultBean<String> saveSalesOrder(String basicInfo,String productListInfo) {
		Product product = new Product();
		product = (Product)ParseUtil.getBeanFromStr(basicInfo, "pojo.sales.salesOrder");
		ResultBean<String> result = new ResultBean<String>();
		if(product.getId() == null){
			// 添加商品时，设置当前时间为添加时间。
			product.setGmtCreate(DateUtil.getNowDate());
			result.setMsg("添加成功！");
		}else{
			product.setGmtModified(DateUtil.getNowDate());
			result.setMsg("修改成功！");
		}
		String productId = String.valueOf(product.getId());
		//保存规格
		List<String> ids = ParseUtil.parseFromStrArray(productListInfo);
		for(String valueId : ids){
		}
		result.setCode(ResultBean.SUCCESS);
		return result;
	}
}

package controller.sales;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pojo.salesOrder.SalesOrderDetailInfo;
import pojo.salesOrder.SalesOrderInfo;
import service.sales.SalesOrderService;


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
		String url = "sales/SalesOrder/orderList";
		mav.setViewName(url);
		mav.addObject("orderList", list);
		return mav;
	}
	
	@RequestMapping("orderDetail")
	public ModelAndView orderDetail(String orderId) {
		List<SalesOrderDetailInfo> list = SalesOrderService.getDetail(orderId);
		ModelAndView mav = new ModelAndView();
		String url = "sales/SalesOrder/orderDetail";
		mav.setViewName(url);
		mav.addObject("detailList", list);
		return mav;
	}

}

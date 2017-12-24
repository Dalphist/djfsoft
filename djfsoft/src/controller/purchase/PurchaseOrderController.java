package controller.purchase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pojo.purchase.PurchaseOrder;
import service.purchase.PurchaseOrderService;

@Controller
@RequestMapping("purchase/purchaseOrder")
public class PurchaseOrderController {
	@Autowired
	PurchaseOrderService purchaseOrderService;

	@RequestMapping("index")
	public ModelAndView productIndex() {
		ModelAndView mav = new ModelAndView();
		String url = "purchase/purchaseOrder/index";
		mav.setViewName(url);
		return mav;
	}
	
	@RequestMapping("orderList")
	public ModelAndView orderList() {
		List<PurchaseOrder> list = purchaseOrderService.orderList();
		ModelAndView mav = new ModelAndView();
		String url = "purchase/purchaseOrder/orderList";
		mav.setViewName(url);
		mav.addObject("orderList", list);
		return mav;
	}

}

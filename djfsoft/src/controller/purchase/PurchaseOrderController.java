package controller.purchase;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import pojo.ResultBean;
import pojo.purchase.PurchaseOrder;
import pojo.purchase.PurchaseOrderDetailInfo;
import pojo.purchase.PurchaseOrderInfo;
import service.purchase.PurchaseOrderService;
import util.DateUtil;
import util.ParseUtil;
import util.SessionUtil;

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
		List<PurchaseOrderInfo> list = purchaseOrderService.orderList();
		ModelAndView mav = new ModelAndView();
		String url = "purchase/purchaseOrder/orderList";
		mav.setViewName(url);
		mav.addObject("orderList", list);
		return mav;
	}
	
	@RequestMapping("orderDetail")
	public ModelAndView orderDetail(String orderId) {
		List<PurchaseOrderDetailInfo> list = purchaseOrderService.getDetail(orderId);
		ModelAndView mav = new ModelAndView();
		String url = "purchase/purchaseOrder/orderDetail";
		mav.setViewName(url);
		mav.addObject("detailList", list);
		return mav;
	}
	
	@RequestMapping("getNewOrderCode")
	@ResponseBody
	public ResultBean<String> getNewOrderCode() {
		ResultBean<String> result = new ResultBean<String>();
		String preStr = "CG"+DateUtil.getDateFormat1(new Date());
		String code = purchaseOrderService.getNewCode(preStr);
		result.setCode(ResultBean.SUCCESS);
		result.setData(code);
		return result;
	}
	
	@RequestMapping("savePurchaseOrder")
	@ResponseBody
	public ResultBean<String> savePurchaseOrder(HttpSession session,String basicInfo,String productListInfo) {
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		purchaseOrder = (PurchaseOrder)ParseUtil.getBeanFromStr(basicInfo, "pojo.purchase.PurchaseOrder");
		//保存操作人
		int userId = SessionUtil.getUserId(session);
		purchaseOrder.setOperaterId(userId);
		
		ResultBean<String> result = new ResultBean<String>();
		if(purchaseOrder.getId() == null){
			purchaseOrder.setOperateDate(DateUtil.getNowDateTime());
			purchaseOrderService.addOrder(purchaseOrder);
			result.setMsg("添加成功！");
		}else{
			result.setMsg("修改成功！");
		}
		String purchaseOrderId = String.valueOf(purchaseOrder.getId());
		//保存订单的货品详情
		List<PurchaseOrderDetailInfo> list = ParseUtil.getBeanListFromStr(productListInfo, "pojo.purchase.PurchaseOrderDetailInfo");
		for(PurchaseOrderDetailInfo detail : list){
			detail.setPurchaseOrderId((Integer.parseInt(purchaseOrderId)));
			purchaseOrderService.addOrderDetail(detail);
		}
		result.setCode(ResultBean.SUCCESS);
		return result;
	}
	
	

}

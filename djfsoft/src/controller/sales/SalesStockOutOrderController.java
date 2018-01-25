package controller.sales;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import pojo.ResultBean;
import pojo.sales.SalesStockOutOrderDetailInfo;
import pojo.sales.SalesStockOutOrderInfo;
import service.sales.SalesStockOutOrderService;
import util.DateUtil;
import util.ParseUtil;


@Controller
@RequestMapping("sales/salesStockOutOrder")
public class SalesStockOutOrderController {
	@Autowired
	SalesStockOutOrderService salesStockOutOrderService;

	@RequestMapping("index")
	public ModelAndView productIndex() {
		ModelAndView mav = new ModelAndView();
		String url = "sales/salesStockOutOrder/index";
		mav.setViewName(url);
		return mav;
	}
	
	@RequestMapping("orderList")
	public ModelAndView orderList() {
		List<SalesStockOutOrderInfo> list = salesStockOutOrderService.orderList();
		ModelAndView mav = new ModelAndView();
		String url = "sales/salesStockOutOrder/orderList";
		mav.setViewName(url);
		mav.addObject("orderList", list);
		return mav;
	}
	
	@RequestMapping("getOrderDetail")
	@ResponseBody
	public ResultBean<SalesStockOutOrderDetailInfo> orderDetail(String orderId) {
		ResultBean<SalesStockOutOrderDetailInfo> result = new ResultBean<SalesStockOutOrderDetailInfo>();
		List<SalesStockOutOrderDetailInfo> list = salesStockOutOrderService.getDetail(orderId);
		result.setDataList(list);
		return result;
	}
	
	@RequestMapping("saveSalesOrder")
	@ResponseBody
	public ResultBean<String> saveSalesOrder(String basicInfo,String productListInfo) {
		SalesStockOutOrderInfo salesStockOutOrder = new SalesStockOutOrderInfo();
		salesStockOutOrder = (SalesStockOutOrderInfo)ParseUtil.getBeanFromStr(basicInfo, "pojo.sales.salesStockOutOrder");
		ResultBean<String> result = new ResultBean<String>();
		if(salesStockOutOrder.getId() == null){
			salesStockOutOrder.setOperateDate(DateUtil.getNowDateTime());
			salesStockOutOrderService.addOrder(salesStockOutOrder);
			result.setMsg("添加成功！");
		}else{
			result.setMsg("修改成功！");
		}
		String salesOrderId = String.valueOf(salesStockOutOrder.getId());
		//保存订单的货品详情
		List<SalesStockOutOrderDetailInfo> list = ParseUtil.getBeanListFromStr(productListInfo, "pojo.sales.SalesStockOutOrderDetailInfo");
		for(SalesStockOutOrderDetailInfo detail : list){
			detail.setStockOutOrderId(Integer.parseInt(salesOrderId));
			salesStockOutOrderService.addOrderDetail(detail);
		}
		result.setCode(ResultBean.SUCCESS);
		return result;
	}
	@RequestMapping("delOrder")
	@ResponseBody
	public ResultBean<String> delOrder(String orderId) {
		ResultBean<String> result = new ResultBean<String>();
		salesStockOutOrderService.delOrderDetail(orderId);
		salesStockOutOrderService.delOrder(orderId);
		result.setMsg("删除成功！");
		return result;
	}
	
	@RequestMapping("getNewOrderCode")
	@ResponseBody
	public ResultBean<String> getNewOrderCode() {
		ResultBean<String> result = new ResultBean<String>();
		String preStr = "XSCK"+DateUtil.getDateFormat1(new Date());
		String code = salesStockOutOrderService.getNewCode(preStr);
		result.setCode(ResultBean.SUCCESS);
		result.setData(code);
		return result;
	}
	
	/**
	 * 出库操作
	 * @return
	 */
	@RequestMapping("stockOut")
	@ResponseBody
	public ResultBean<String> stockOut(String orderId) {
		ResultBean<String> result = new ResultBean<String>();
		List<SalesStockOutOrderDetailInfo> details = salesStockOutOrderService.getDetail(orderId);
		for(SalesStockOutOrderDetailInfo detail : details){
			int productId = detail.getProductId();
			double quantity = detail.getQuantity();
			
		}
		result.setCode(ResultBean.SUCCESS);
		return result;
	}
}

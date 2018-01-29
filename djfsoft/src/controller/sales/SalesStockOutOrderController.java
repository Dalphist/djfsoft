package controller.sales;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import pojo.ResultBean;
import pojo.sales.SalesOrderDetailInfo;
import pojo.sales.SalesStockOutOrderInfo;
import pojo.stock.StockOutOrderDetailInfo;
import pojo.stock.StockOutOrderInfo;
import service.sales.SalesOrderService;
import service.sales.SalesStockOutOrderService;
import service.stock.StockService;
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
	public ResultBean<StockOutOrderDetailInfo> orderDetail(String orderId) {
		ResultBean<StockOutOrderDetailInfo> result = new ResultBean<StockOutOrderDetailInfo>();
		List<StockOutOrderDetailInfo> list = salesStockOutOrderService.getDetail(orderId);
		result.setDataList(list);
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
		String code = getCode();
		result.setCode(ResultBean.SUCCESS);
		result.setData(code);
		return result;
	}
	/**
	 * 获取最新编号
	 */
	public String getCode(){
		String preStr = "XSCK"+DateUtil.getDateFormat1(new Date());
		return salesStockOutOrderService.getNewCode(preStr);
	}
	@Autowired
	StockService stockService;
	@Autowired
	SalesOrderService salesOrderService;
	
	@RequestMapping("toStockOut")
	@ResponseBody
	public ResultBean<String> toStockOut(String selectOrderIds) {
		StockOutOrderInfo stockOutOrder = new StockOutOrderInfo();
		stockOutOrder.setOrderCode(getCode());
		salesStockOutOrderService.addOrder(stockOutOrder);
		int stockOutOrderId = stockOutOrder.getId();
		List<String> ids = ParseUtil.parseFromStrArray(selectOrderIds);
		for(String orderId : ids){
			List<SalesOrderDetailInfo> details = salesOrderService.getDetail(orderId);
			for(SalesOrderDetailInfo detail : details){
				StockOutOrderDetailInfo out = new StockOutOrderDetailInfo();
				out.setStockOutOrderId(stockOutOrderId);
				out.setProductId(detail.getProductId());
				out.setNormalQuantity(detail.getQuantity());
				out.setSalesOrderId(Integer.parseInt(orderId));
				//出库减库存
				stockService.stockOut(out);
			}
		}
		ResultBean<String> result = new ResultBean<String>();
		result.setCode(ResultBean.SUCCESS);
		return result;
	}
}

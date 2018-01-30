package controller.sales;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import pojo.ResultBean;
import pojo.User;
import pojo.sales.SalesOrder;
import pojo.sales.SalesOrderDetailInfo;
import pojo.sales.SalesOrderInfo;
import service.sales.SalesOrderService;
import util.DateUtil;
import util.ParseUtil;


@Controller
@RequestMapping("sales/salesOrder")
public class SalesOrderController {
	@Autowired
	SalesOrderService salesOrderService;

	@RequestMapping("index")
	public ModelAndView productIndex() {
		ModelAndView mav = new ModelAndView();
		String url = "sales/salesOrder/index";
		mav.setViewName(url);
		return mav;
	}
	
	@RequestMapping("orderList")
	public ModelAndView orderList() {
		List<SalesOrderInfo> list = salesOrderService.orderList();
		ModelAndView mav = new ModelAndView();
		String url = "sales/salesOrder/orderList";
		mav.setViewName(url);
		mav.addObject("orderList", list);
		return mav;
	}
	
	@RequestMapping("getOrderDetail")
	@ResponseBody
	public ResultBean<SalesOrderDetailInfo> orderDetail(String orderId) {
		ResultBean<SalesOrderDetailInfo> result = new ResultBean<SalesOrderDetailInfo>();
		List<SalesOrderDetailInfo> list = salesOrderService.getDetail(orderId);
		result.setDataList(list);
		return result;
	}
	
	@RequestMapping("saveSalesOrder")
	@ResponseBody
	public ResultBean<String> saveSalesOrder(HttpSession session,String basicInfo,String productListInfo) {
		SalesOrder salesOrder = new SalesOrder();
		salesOrder = (SalesOrder)ParseUtil.getBeanFromStr(basicInfo, "pojo.sales.SalesOrder");
		//保存操作人
		int userId = getUserId(session);
		salesOrder.setOperaterId(userId);
		
		ResultBean<String> result = new ResultBean<String>();
		if(salesOrder.getId() == null){
			salesOrder.setOperateDate(DateUtil.getNowDateTime());
			salesOrderService.addOrder(salesOrder);
			result.setMsg("添加成功！");
		}else{
			result.setMsg("修改成功！");
		}
		String salesOrderId = String.valueOf(salesOrder.getId());
		//保存订单的货品详情
		List<SalesOrderDetailInfo> list = ParseUtil.getBeanListFromStr(productListInfo, "pojo.sales.SalesOrderDetailInfo");
		for(SalesOrderDetailInfo detail : list){
			detail.setSalesOrderId(Integer.parseInt(salesOrderId));
			salesOrderService.addOrderDetail(detail);
		}
		result.setCode(ResultBean.SUCCESS);
		return result;
	}
	/**
	 * 通过session 获取userId
	 * @param session
	 * @return
	 */
	private int getUserId(HttpSession session) {
		User user = (User) session.getAttribute("user");
		int userId = user.getId();
		return userId;
	}
	@RequestMapping("delOrder")
	@ResponseBody
	public ResultBean<String> delOrder(String orderId) {
		ResultBean<String> result = new ResultBean<String>();
		salesOrderService.delOrderDetail(orderId);
		salesOrderService.delOrder(orderId);
		result.setMsg("删除成功！");
		return result;
	}
	
	@RequestMapping("getNewOrderCode")
	@ResponseBody
	public ResultBean<String> getNewOrderCode() {
		ResultBean<String> result = new ResultBean<String>();
		String preStr = "XS"+DateUtil.getDateFormat1(new Date());
		String code = salesOrderService.getNewCode(preStr);
		result.setCode(ResultBean.SUCCESS);
		result.setData(code);
		return result;
	}
	
}

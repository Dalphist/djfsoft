package controller.purchase;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import util.PageInfo;
import util.ParseUtil;
import util.SessionUtil;
import util.enumSet.PurchaseStateEnum;

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
	public ModelAndView orderList(HttpServletRequest request, HttpServletResponse response) {
		List<PurchaseOrderInfo> list = purchaseOrderService.orderList();
		ModelAndView mav = new ModelAndView();
		String url = "purchase/purchaseOrder/orderList";
		mav.setViewName(url);
		mav.addObject("orderList", list);
		
		//*************
        int currentPage = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
        int pageSize = 3;
        if (currentPage <= 0) {
            currentPage = 1;
        }
        int currentResult = (currentPage - 1) * pageSize;

        PageInfo page = new PageInfo();
        page.setShowCount(pageSize);
        page.setCurrentResult(currentResult);
        
        System.out.println(request.getRequestURI());
        System.out.println(request.getQueryString());

        int totalCount = page.getTotalResult();

        int lastPage = 0;
        if (totalCount % pageSize == 0) {
            lastPage = totalCount % pageSize;
        } else {
            lastPage = 1 + totalCount / pageSize;
        }

        if (currentPage >= lastPage) {
            currentPage = lastPage;
        }
        mav.addObject("list", list);
		
		return mav;
	}
	
	@RequestMapping("getOrderDetail")
	@ResponseBody
	public ResultBean<PurchaseOrderDetailInfo> orderDetail(String orderId) {
		ResultBean<PurchaseOrderDetailInfo> result = new ResultBean<PurchaseOrderDetailInfo>();
		List<PurchaseOrderDetailInfo> list = purchaseOrderService.getDetail(orderId);
		result.setDataList(list);
		return result;
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
	
	
	
	@RequestMapping("checkOrder")
	@ResponseBody
	public ResultBean<String> checkOrder(String selectOrderIds) {
		ResultBean<String> result = new ResultBean<String>();
		List<String> idList = ParseUtil.parseFromStrArray(selectOrderIds);
		for(String id : idList){
			purchaseOrderService.updateState(id, PurchaseStateEnum.CHECKED.getState());
		}
		result.setMsg("审核完成!");
		return result;
	}
	

}

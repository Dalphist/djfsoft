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
import pojo.purchase.PurchaseOrderDetailInfo;
import pojo.stock.StockInOrderDetailInfo;
import pojo.stock.StockInOrderInfo;
import service.purchase.PurchaseOrderService;
import service.purchase.PurchaseStockInOrderService;
import service.stock.StockService;
import util.DateUtil;
import util.ParseUtil;
import util.SessionUtil;
import util.enumSet.PurchaseStateEnum;
import util.enumSet.StockInStateEnum;


@Controller
@RequestMapping("purchase/purchaseStockInOrder")
public class PurchaseStockInOrderController {
	@Autowired
	PurchaseStockInOrderService purchaseStockInOrderService;

	@RequestMapping("index")
	public ModelAndView productIndex() {
		ModelAndView mav = new ModelAndView();
		String url = "purchase/purchaseStockInOrder/index";
		mav.setViewName(url);
		return mav;
	}
	
	@RequestMapping("orderList")
	public ModelAndView orderList() {
		List<StockInOrderInfo> list = purchaseStockInOrderService.orderList();
		ModelAndView mav = new ModelAndView();
		String url = "purchase/purchaseStockInOrder/orderList";
		mav.setViewName(url);
		mav.addObject("orderList", list);
		return mav;
	}
	
	@RequestMapping("getOrderDetail")
	@ResponseBody
	public ResultBean<StockInOrderDetailInfo> orderDetail(String orderId) {
		ResultBean<StockInOrderDetailInfo> result = new ResultBean<StockInOrderDetailInfo>();
		List<StockInOrderDetailInfo> list = purchaseStockInOrderService.getDetail(orderId);
		result.setDataList(list);
		return result;
	}
	
	@RequestMapping("delOrder")
	@ResponseBody
	public ResultBean<String> delOrder(String orderId) {
		ResultBean<String> result = new ResultBean<String>();
		purchaseStockInOrderService.delOrderDetail(orderId);
		purchaseStockInOrderService.delOrder(orderId);
		result.setMsg("删除成功！");
		return result;
	}
	
	@RequestMapping("saveOrder")
	@ResponseBody
	public ResultBean<String> saveOrder(String selectOrderIds,String typeId,HttpSession session) {
		//保存入库单
		StockInOrderInfo stockInOrder = new StockInOrderInfo();
		stockInOrder.setOperaterId(SessionUtil.getUserId(session));
		stockInOrder.setOrderCode(getCode());
		stockInOrder.setOperateDate(DateUtil.getNowDateTime());
		stockInOrder.setTypeId(Integer.parseInt(typeId));
		purchaseStockInOrderService.addOrder(stockInOrder);
		int stockInOrderId = stockInOrder.getId();
		//遍历所选采购订单
		List<String> ids = ParseUtil.parseFromStrArray(selectOrderIds);
		for(String purchaseOrderId : ids){
			//保存出库单详情
			saveStockInOrderDetail(stockInOrderId, purchaseOrderId);
			//修改采购订单的状态 -- 到货
			purchaseOrderService.updateState(purchaseOrderId, PurchaseStateEnum.ARRIVED.getState());
		}
		
		ResultBean<String> result = new ResultBean<String>();
		result.setCode(ResultBean.SUCCESS);
		result.setMsg("已生成入库单！");
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
	 * 入库操作
	 */
	
	@RequestMapping("toStockIn")
	@ResponseBody
	public ResultBean<String> toStockIn(String selectOrderIds,HttpSession session) {
		//遍历所选销售订单
		List<String> ids = ParseUtil.parseFromStrArray(selectOrderIds);
		for(String stockInOrderId : ids){
			//入库修改库存
			stockIn(stockInOrderId);
			//修改采购订单的状态 -- 已入库
			updatePurchaseOrderState(stockInOrderId,PurchaseStateEnum.STOCKIN.getState());
			//修改入库单的状态  -- 入库
			updateStockInOrderState(stockInOrderId,StockInStateEnum.STOCKIN.getState());
		}
		
		ResultBean<String> result = new ResultBean<String>();
		result.setCode(ResultBean.SUCCESS);
		result.setMsg("入库成功！");
		return result;
	}
	
	/**
	 * 修改入库单状态
	 * @param stockInOrderId
	 * @param state
	 */
	private void updateStockInOrderState(String stockInOrderId, String state) {
		StockInOrderInfo order = purchaseStockInOrderService.getOrderById(stockInOrderId);
		order.setStateFlag(Integer.parseInt(state));
		purchaseStockInOrderService.updateOrder(order);
	}

	/**
	 * 修改入库单对应的采购订单的状态
	 */
	@Autowired
	PurchaseOrderService purchaseOrderService;
	private void updatePurchaseOrderState(String stockInOrderId,String state) {
		List<String> purchaseIdList = purchaseStockInOrderService.getPurchaseIdList(stockInOrderId);
		for(String purchaseOrderId : purchaseIdList){
			purchaseOrderService.updateState(purchaseOrderId, state);
		}
	}
	
	/**
	 * 修改库存
	 */
	@Autowired
	StockService stockService;
	private void stockIn(String stockInOrderId) {
		List<StockInOrderDetailInfo> details = purchaseStockInOrderService.getDetail(stockInOrderId);
		for(StockInOrderDetailInfo detail : details){
			StockInOrderDetailInfo in = new StockInOrderDetailInfo();
			in.setProductId(detail.getProductId());
			in.setNormalQuantity(detail.getNormalQuantity());
			stockService.stockIn(in);
		}
	}

	private void saveStockInOrderDetail(int stockInOrderId, String purchaseOrderId) {
		List<PurchaseOrderDetailInfo> details = purchaseOrderService.getDetail(purchaseOrderId);
		for(PurchaseOrderDetailInfo detail : details){
			StockInOrderDetailInfo in = new StockInOrderDetailInfo();
			in.setStockInOrderId(stockInOrderId);
			in.setProductId(detail.getProductId());
			in.setNormalQuantity(detail.getQuantity());
			in.setPurchaseOrderId(Integer.parseInt(purchaseOrderId));
			purchaseStockInOrderService.addOrderDetail(in);
		}
	}
	
	/**
	 * 获取最新编号
	 */
	private String getCode(){
		String preStr = "CGRK"+DateUtil.getDateFormat1(new Date());
		return purchaseStockInOrderService.getNewCode(preStr);
	}
}

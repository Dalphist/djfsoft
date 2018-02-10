package service.purchase;

import java.util.List;

import pojo.stock.StockInOrderDetailInfo;
import pojo.stock.StockInOrderInfo;

public interface PurchaseStockInOrderService {
	void addOrder(StockInOrderInfo order);

	void addOrderDetail(StockInOrderDetailInfo detailInfo);

	void delOrder(String orderId);

	void delOrderDetail(String orderId);

	void updateOrder(StockInOrderInfo order);

	List<StockInOrderInfo> orderList();

	List<StockInOrderDetailInfo> getDetail(String orderId);
	
	String getNewCode(String preStr);

}

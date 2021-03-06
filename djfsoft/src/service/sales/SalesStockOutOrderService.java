package service.sales;

import java.util.List;

import pojo.stock.StockOutOrderDetailInfo;
import pojo.stock.StockOutOrderInfo;

public interface SalesStockOutOrderService {
	void addOrder(StockOutOrderInfo order);

	void addOrderDetail(StockOutOrderDetailInfo detailInfo);

	void delOrder(String orderId);

	void delOrderDetail(String orderId);

	void updateOrder(StockOutOrderInfo order);

	List<StockOutOrderInfo> orderList();

	List<StockOutOrderDetailInfo> getDetail(String orderId);
	
	String getNewCode(String preStr);

}

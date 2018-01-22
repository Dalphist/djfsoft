package service.sales;

import java.util.List;

import pojo.sales.SalesStockOutOrderDetailInfo;
import pojo.sales.SalesStockOutOrderInfo;

public interface SalesStockOutOrderService {
	void addOrder(SalesStockOutOrderInfo order);

	void addOrderDetail(SalesStockOutOrderDetailInfo detailInfo);

	void delOrder(String orderId);

	void delOrderDetail(String orderId);

	void updateOrder(SalesStockOutOrderInfo order);

	List<SalesStockOutOrderInfo> orderList();

	List<SalesStockOutOrderDetailInfo> getDetail(String orderId);
	
	String getNewCode(String preStr);

}

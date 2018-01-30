package service.sales;

import java.util.List;

import pojo.sales.SalesOrder;
import pojo.sales.SalesOrderDetailInfo;
import pojo.sales.SalesOrderInfo;

public interface SalesOrderService {
	void addOrder(SalesOrder salesOrder);

	void addOrderDetail(SalesOrderDetailInfo salesOrderDetailInfo);

	void delOrder(String orderId);

	void delOrderDetail(String orderId);

	void updateOrder(SalesOrder salesOrder);

	List<SalesOrderInfo> orderList();

	List<SalesOrderDetailInfo> getDetail(String orderId);
	
	String getNewCode(String preStr);
	
	void updateState(String orderId,String state);

}

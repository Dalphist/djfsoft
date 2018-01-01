package mapper.sales;

import java.util.List;

import pojo.salesOrder.SalesOrder;
import pojo.salesOrder.SalesOrderDetailInfo;
import pojo.salesOrder.SalesOrderInfo;

public interface SalesOrderMapper {
	public void addOrder(SalesOrder salesOrder);

	public void addOrderDetail(SalesOrderDetailInfo salesOrderDetailInfo);

	public void delOrder(String orderId);

	public void delOrderDetail(String orderId);

	public void updateOrder(SalesOrder salesOrder);

	public List<SalesOrderInfo> orderList();

	public List<SalesOrderDetailInfo> getDetail(String orderId);
}

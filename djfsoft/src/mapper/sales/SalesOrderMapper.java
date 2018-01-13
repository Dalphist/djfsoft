package mapper.sales;

import java.util.List;

import pojo.sales.SalesOrder;
import pojo.sales.SalesOrderDetailInfo;
import pojo.sales.SalesOrderInfo;

public interface SalesOrderMapper {
	public void addOrder(SalesOrder salesOrder);

	public void addOrderDetail(SalesOrderDetailInfo salesOrderDetailInfo);

	public void delOrder(String orderId);

	public void delOrderDetail(String orderId);

	public void updateOrder(SalesOrder salesOrder);

	public List<SalesOrderInfo> orderList();

	public List<SalesOrderDetailInfo> getDetail(String orderId);
	//得到最新编码，即：原最大编码加1	preStr:XS+时间
	public String getNewCode(String preStr);
}

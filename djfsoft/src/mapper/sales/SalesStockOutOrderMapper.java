package mapper.sales;

import java.util.List;

import pojo.sales.SalesStockOutOrderDetailInfo;
import pojo.sales.SalesStockOutOrderInfo;

public interface SalesStockOutOrderMapper {
	public void addOrder(SalesStockOutOrderInfo order);

	public void addOrderDetail(SalesStockOutOrderDetailInfo detailInfo);

	public void delOrder(String orderId);

	public void delOrderDetail(String orderId);

	public void updateOrder(SalesStockOutOrderInfo order);

	public List<SalesStockOutOrderInfo> orderList();

	public List<SalesStockOutOrderDetailInfo> getDetail(String orderId);
	
	public String getNewCode(String preStr);
}

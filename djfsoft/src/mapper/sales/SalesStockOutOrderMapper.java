package mapper.sales;

import java.util.List;

import pojo.sales.SalesStockOutOrderInfo;
import pojo.stock.StockOutOrderDetailInfo;
import pojo.stock.StockOutOrderInfo;

public interface SalesStockOutOrderMapper {
	public void addOrder(StockOutOrderInfo order);

	public void addOrderDetail(StockOutOrderDetailInfo detailInfo);

	public void delOrder(String orderId);

	public void delOrderDetail(String orderId);

	public void updateOrder(StockOutOrderInfo order);

	public List<SalesStockOutOrderInfo> orderList();

	public List<StockOutOrderDetailInfo> getDetail(String orderId);
	
	public String getNewCode(String preStr);
}

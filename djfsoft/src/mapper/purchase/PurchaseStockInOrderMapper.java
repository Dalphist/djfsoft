package mapper.purchase;

import java.util.List;

import pojo.stock.StockInOrderDetailInfo;
import pojo.stock.StockInOrderInfo;

public interface PurchaseStockInOrderMapper {
	public void addOrder(StockInOrderInfo order);

	public void addOrderDetail(StockInOrderDetailInfo detailInfo);

	public void delOrder(String orderId);

	public void delOrderDetail(String orderId);

	public void updateOrder(StockInOrderInfo order);

	public List<StockInOrderInfo> orderList();

	public List<StockInOrderDetailInfo> getDetail(String orderId);
	
	public String getNewCode(String preStr);
}

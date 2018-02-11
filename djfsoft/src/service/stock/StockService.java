package service.stock;

import java.util.List;

import pojo.stock.StockInOrderDetailInfo;
import pojo.stock.StockInfo;
import pojo.stock.StockOutOrderDetailInfo;

public interface StockService {
	List<StockInfo> getStockList();
	void stockOut(StockOutOrderDetailInfo stockOutInfo);
	void stockIn(StockInOrderDetailInfo stockInOrderDetailInfo);
}

package mapper.stock;

import java.util.List;

import pojo.stock.StockInInfo;
import pojo.stock.StockInfo;
import pojo.stock.StockOutOrderDetailInfo;

public interface StockMapper {
	List<StockInfo> getStockList();
	void stockOut(StockOutOrderDetailInfo stockOutInfo);
	void stockIn(StockInInfo stockInInfo);
}

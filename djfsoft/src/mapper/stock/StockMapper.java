package mapper.stock;

import java.util.List;

import pojo.stock.StockInInfo;
import pojo.stock.StockInfo;
import pojo.stock.StockOutInfo;

public interface StockMapper {
	List<StockInfo> getStockList();
	void stockOut(StockOutInfo stockOutInfo);
	void stockIn(StockInInfo stockInInfo);
}

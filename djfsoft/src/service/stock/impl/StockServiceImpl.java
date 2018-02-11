package service.stock.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mapper.stock.StockMapper;
import pojo.stock.StockInOrderDetailInfo;
import pojo.stock.StockInfo;
import pojo.stock.StockOutOrderDetailInfo;
import service.stock.StockService;

@Service
public class StockServiceImpl implements StockService {
	@Autowired
	StockMapper stockMapper;
	@Override
	public List<StockInfo> getStockList() {
		return stockMapper.getStockList();
	}

	@Override
	public void stockOut(StockOutOrderDetailInfo stockOutInfo) {
		stockMapper.stockOut(stockOutInfo);
		return;
	}

	@Override
	public void stockIn(StockInOrderDetailInfo stockInOrderDetailInfo) {
		stockMapper.stockIn(stockInOrderDetailInfo);
		return;
	}

}

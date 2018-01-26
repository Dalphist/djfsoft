package service.stock.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mapper.stock.StockMapper;
import pojo.stock.StockInInfo;
import pojo.stock.StockInfo;
import pojo.stock.StockOutInfo;
import service.stock.StockService;

@Service
public class StockServiceImpl implements StockService {
	@Autowired
	StockMapper stockMapper;
	@Override
	public List<StockInfo> getStockList() {
		return null;
	}

	@Override
	public void stockOut(StockOutInfo stockOutInfo) {
		stockMapper.stockOut(stockOutInfo);
		return;
	}

	@Override
	public void stockIn(StockInInfo stockInInfo) {
		stockMapper.stockIn(stockInInfo);
		return;
	}

}

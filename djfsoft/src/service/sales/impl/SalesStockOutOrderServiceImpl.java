package service.sales.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mapper.sales.SalesStockOutOrderMapper;
import pojo.sales.SalesStockOutOrderDetailInfo;
import pojo.sales.SalesStockOutOrderInfo;
import service.sales.SalesStockOutOrderService;

@Service
public class SalesStockOutOrderServiceImpl implements SalesStockOutOrderService {
	@Autowired
	SalesStockOutOrderMapper salesStockOutOrderMapper;

	@Override
	public void addOrder(SalesStockOutOrderInfo order) {
		salesStockOutOrderMapper.addOrder(order);
		return;
	}

	@Override
	public void addOrderDetail(SalesStockOutOrderDetailInfo detailInfo) {
		salesStockOutOrderMapper.addOrderDetail(detailInfo);
		return;
	}

	@Override
	public void delOrder(String orderId) {
		salesStockOutOrderMapper.delOrder(orderId);
		return;
	}

	@Override
	public void delOrderDetail(String orderId) {
		salesStockOutOrderMapper.delOrderDetail(orderId);
		return;
	}

	@Override
	public void updateOrder(SalesStockOutOrderInfo order) {
		salesStockOutOrderMapper.updateOrder(order);
		return;
	}

	@Override
	public List<SalesStockOutOrderInfo> orderList() {
		return salesStockOutOrderMapper.orderList();
	}

	@Override
	public List<SalesStockOutOrderDetailInfo> getDetail(String orderId) {
		return salesStockOutOrderMapper.getDetail(orderId);
	}

	@Override
	public String getNewCode(String preStr) {
		return salesStockOutOrderMapper.getNewCode(preStr);
	}

}

package service.purchase.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mapper.purchase.PurchaseStockInOrderMapper;
import pojo.stock.StockInOrderDetailInfo;
import pojo.stock.StockInOrderInfo;
import service.purchase.PurchaseStockInOrderService;

@Service
public class PurchaseStockInOrderServiceImpl implements PurchaseStockInOrderService {
	@Autowired
	PurchaseStockInOrderMapper purchaseStockInOrderMapper;

	@Override
	public void addOrder(StockInOrderInfo order) {
		purchaseStockInOrderMapper.addOrder(order);
		return;
	}

	@Override
	public void addOrderDetail(StockInOrderDetailInfo detailInfo) {
		purchaseStockInOrderMapper.addOrderDetail(detailInfo);
		return;
	}

	@Override
	public void delOrder(String orderId) {
		purchaseStockInOrderMapper.delOrder(orderId);
		return;
	}

	@Override
	public void delOrderDetail(String orderId) {
		purchaseStockInOrderMapper.delOrderDetail(orderId);
		return;
	}

	@Override
	public void updateOrder(StockInOrderInfo order) {
		purchaseStockInOrderMapper.updateOrder(order);
	}

	@Override
	public List<StockInOrderInfo> orderList() {
		return purchaseStockInOrderMapper.orderList();
	}

	@Override
	public List<StockInOrderDetailInfo> getDetail(String orderId) {
		return purchaseStockInOrderMapper.getDetail(orderId);
	}

	@Override
	public String getNewCode(String preStr) {
		return purchaseStockInOrderMapper.getNewCode(preStr);
	}

	@Override
	public List<String> getPurchaseIdList(String stockInOrderId) {
		return purchaseStockInOrderMapper.getPurchaseIdList(stockInOrderId);
	}

	@Override
	public StockInOrderInfo getOrderById(String stockInOrderId) {
		return purchaseStockInOrderMapper.getOrderById(stockInOrderId);
	}


}

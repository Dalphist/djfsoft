package service.purchase.impl;

import java.util.List;

import mapper.purchase.PurchaseOrderMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pojo.purchase.PurchaseOrder;
import pojo.purchase.PurchaseOrderDetailInfo;
import service.purchase.PurchaseOrderService;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {
	@Autowired
	PurchaseOrderMapper purchaseOrderMapper;

	@Override
	public void addOrder(PurchaseOrder purchaseOrder) {
		purchaseOrderMapper.addOrder(purchaseOrder);
		return;
	}

	@Override
	public void addOrderDetail(PurchaseOrderDetailInfo purchaseOrderDetailInfo) {
		purchaseOrderMapper.addOrderDetail(purchaseOrderDetailInfo);
		return;
	}

	@Override
	public void delOrder(String orderId) {
		purchaseOrderMapper.delOrder(orderId);
		return;
	}

	@Override
	public void delOrderDetail(String orderId) {
		purchaseOrderMapper.delOrderDetail(orderId);
		return;
	}

	@Override
	public void updateOrder(PurchaseOrder purchaseOrder) {
		purchaseOrderMapper.updateOrder(purchaseOrder);
		return;
	}
	
	@Override
	public List<PurchaseOrder> orderList() {
		return purchaseOrderMapper.orderList();
	}
	
	@Override
	public List<PurchaseOrderDetailInfo> getDetail(String orderId) {
		return purchaseOrderMapper.getDetail(orderId);
	}

	
}

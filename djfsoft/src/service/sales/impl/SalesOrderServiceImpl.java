package service.sales.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mapper.sales.SalesOrderMapper;
import pojo.sales.SalesOrder;
import pojo.sales.SalesOrderDetailInfo;
import pojo.sales.SalesOrderInfo;
import service.sales.SalesOrderService;

@Service
public class SalesOrderServiceImpl implements SalesOrderService {
	@Autowired
	SalesOrderMapper salesOrderMapper;

	@Override
	public void addOrder(SalesOrder SalesOrder) {
		salesOrderMapper.addOrder(SalesOrder);
		return;
	}

	@Override
	public void addOrderDetail(SalesOrderDetailInfo SalesOrderDetailInfo) {
		salesOrderMapper.addOrderDetail(SalesOrderDetailInfo);
		return;
	}

	@Override
	public void delOrder(String orderId) {
		salesOrderMapper.delOrder(orderId);
		return;
	}

	@Override
	public void delOrderDetail(String orderId) {
		salesOrderMapper.delOrderDetail(orderId);
		return;
	}

	@Override
	public void updateOrder(SalesOrder SalesOrder) {
		salesOrderMapper.updateOrder(SalesOrder);
		return;
	}

	@Override
	public List<SalesOrderInfo> orderList() {
		return salesOrderMapper.orderList();
	}

	@Override
	public List<SalesOrderDetailInfo> getDetail(String orderId) {
		return salesOrderMapper.getDetail(orderId);
	}

	@Override
	public String getNewCode(String preStr) {
		return salesOrderMapper.getNewCode(preStr);
	}

}

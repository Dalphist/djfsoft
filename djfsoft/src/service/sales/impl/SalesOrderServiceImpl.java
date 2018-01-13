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
	SalesOrderMapper SalesOrderMapper;

	@Override
	public void addOrder(SalesOrder SalesOrder) {
		SalesOrderMapper.addOrder(SalesOrder);
		return;
	}

	@Override
	public void addOrderDetail(SalesOrderDetailInfo SalesOrderDetailInfo) {
		SalesOrderMapper.addOrderDetail(SalesOrderDetailInfo);
		return;
	}

	@Override
	public void delOrder(String orderId) {
		SalesOrderMapper.delOrder(orderId);
		return;
	}

	@Override
	public void delOrderDetail(String orderId) {
		SalesOrderMapper.delOrderDetail(orderId);
		return;
	}

	@Override
	public void updateOrder(SalesOrder SalesOrder) {
		SalesOrderMapper.updateOrder(SalesOrder);
		return;
	}

	@Override
	public List<SalesOrderInfo> orderList() {
		return SalesOrderMapper.orderList();
	}

	@Override
	public List<SalesOrderDetailInfo> getDetail(String orderId) {
		return SalesOrderMapper.getDetail(orderId);
	}

	@Override
	public String getNewCode(String preStr) {
		return SalesOrderMapper.getNewCode(preStr);
	}

}

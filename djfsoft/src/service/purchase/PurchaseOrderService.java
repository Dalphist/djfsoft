package service.purchase;

import java.util.List;

import pojo.purchase.PurchaseOrder;
import pojo.purchase.PurchaseOrderDetailInfo;

public interface PurchaseOrderService {
	void addOrder(PurchaseOrder purchaseOrder);

	void addOrderDetail(PurchaseOrderDetailInfo purchaseOrderDetailInfo);

	void delOrder(String orderId);

	void delOrderDetail(String orderId);

	void updateOrder(PurchaseOrder purchaseOrder);

	List<PurchaseOrder> orderList();

	List<PurchaseOrderDetailInfo> getDetail(String orderId);

}

package service.purchase;

import java.util.List;

import pojo.purchase.PurchaseOrder;
import pojo.purchase.PurchaseOrderDetailInfo;
import pojo.purchase.PurchaseOrderInfo;

public interface PurchaseOrderService {
	void addOrder(PurchaseOrder purchaseOrder);

	void addOrderDetail(PurchaseOrderDetailInfo purchaseOrderDetailInfo);

	void delOrder(String orderId);

	void delOrderDetail(String orderId);

	void updateOrder(PurchaseOrder purchaseOrder);

	List<PurchaseOrderInfo> orderList();

	List<PurchaseOrderDetailInfo> getDetail(String orderId);
	
	String getNewCode(String preStr);
	
	void updateState(String orderId,String state);

}

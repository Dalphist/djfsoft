package mapper.purchase;

import java.util.List;

import pojo.purchase.PurchaseOrder;
import pojo.purchase.PurchaseOrderDetailInfo;

public interface PurchaseOrderMapper {
	public void addOrder(PurchaseOrder purchaseOrder);

	public void addOrderDetail(PurchaseOrderDetailInfo purchaseOrderDetailInfo);

	public void delOrder(String orderId);

	public void delOrderDetail(String orderId);

	public void updateOrder(PurchaseOrder purchaseOrder);

	public List<PurchaseOrder> orderList();

	public List<PurchaseOrderDetailInfo> getDetail(String orderId);
}

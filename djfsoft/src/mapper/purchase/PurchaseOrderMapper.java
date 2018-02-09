package mapper.purchase;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.purchase.PurchaseOrder;
import pojo.purchase.PurchaseOrderDetailInfo;
import pojo.purchase.PurchaseOrderInfo;

public interface PurchaseOrderMapper {
	public void addOrder(PurchaseOrder purchaseOrder);

	public void addOrderDetail(PurchaseOrderDetailInfo purchaseOrderDetailInfo);

	public void delOrder(String orderId);

	public void delOrderDetail(String orderId);

	public void updateOrder(PurchaseOrder purchaseOrder);

	public List<PurchaseOrderInfo> orderList();

	public List<PurchaseOrderDetailInfo> getDetail(String orderId);
	
	//得到最新编码，即：原最大编码加1	preStr:cg+时间
	public String getNewCode(String preStr);
	//修改订单状态
	public void updateState(@Param("orderId") String orderId,@Param("state") String state);
}

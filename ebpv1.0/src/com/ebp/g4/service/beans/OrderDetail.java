/**
 * 类名：OrderDetail
 * 对应界面内容：订单详情界面所需的信息
 */

package com.ebp.g4.service.beans;

public class OrderDetail
{   

    private String orderNumber;     // 订单编号

    private String goodsNumber;     // 商品编号

    private String storeName;       // 店铺名称

    private String orderDate;         // 交易时间

    private String receiver;        // 收货人

    private String receiveAddress;  // 收获地址

    private String phone;           // 手机号

    private String logisticsName;   // 物流公司名称

    public String getOrderNumber()
    {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber)
    {
        this.orderNumber = orderNumber;
    }

    public String getGoodsNumber()
    {
        return goodsNumber;
    }

    public void setGoodsNumber(String goodsNumber)
    {
        this.goodsNumber = goodsNumber;
    }

    public String getStoreName()
    {
        return storeName;
    }

    public void setStoreName(String storeName)
    {
        this.storeName = storeName;
    }

    public String getOrderDate()
    {
        return orderDate;
    }

    public void setOrderDate(String orderDate)
    {
        this.orderDate = orderDate;
    }

    public String getReceiver()
    {
        return receiver;
    }

    public void setReceiver(String receiver)
    {
        this.receiver = receiver;
    }

    public String getReceiveAddress()
    {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress)
    {
        this.receiveAddress = receiveAddress;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getLogisticsName()
    {
        return logisticsName;
    }

    public void setLogisticsName(String logisticsName)
    {
        this.logisticsName = logisticsName;
    }
    
    @Override
    public boolean equals(Object obj)
    {
        OrderDetail od = (OrderDetail)obj;
        
        if ((this.goodsNumber == null ? "" : this.goodsNumber).equals(od.goodsNumber) &&
            (this.logisticsName == null ? "" : this.logisticsName).equals(od.logisticsName) &&
            (this.orderDate == null ? "" : this.orderDate).equals(od.orderDate) &&
            (this.orderNumber == null ? "" : this.orderNumber).equals(od.orderNumber) &&
            (this.phone == null ? "" : this.phone).equals(od.phone) &&
            (this.receiveAddress == null ? "" : this.receiveAddress).equals(od.receiveAddress) &&
            (this.receiver == null ? "" : this.receiver).equals(od.receiver) &&
            (this.storeName == null ? "" : this.storeName).equals(od.storeName)){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public String toString()
    {
        return "OrderDetail [orderNumber=" + orderNumber + ", goodsNumber="
                + goodsNumber + ", storeName=" + storeName + ", orderDate="
                + orderDate + ", receiver=" + receiver + ", receiveAddress="
                + receiveAddress + ", phone=" + phone + ", logisticsName="
                + logisticsName + "]";
    }
}

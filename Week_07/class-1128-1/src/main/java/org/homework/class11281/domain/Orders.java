package org.homework.class11281.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;


@Table(name = "orders")
public class Orders {
    /**
     * ID
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 订单号
     */
    @Column(name = "oid")
    private String oid;

    /**
     * 运费
     */
    @Column(name = "deliver_fee")
    private BigDecimal deliverFee;

    /**
     * 应付金额
     */
    @Column(name = "payable_fee")
    private BigDecimal payableFee;

    /**
     * 订单金额
     */
    @Column(name = "total_price")
    private BigDecimal totalPrice;

    /**
     * 支付方式 0:到付 1:在线 2:邮局 3:公司转帐
     */
    @Column(name = "payment_way")
    private Integer paymentWay;

    /**
     * 货到付款方式.1现金,2POS刷卡
     */
    @Column(name = "payment_cash")
    private Integer paymentCash;

    /**
     * 送货时间
     */
    @Column(name = "delivery")
    private Date delivery;

    /**
     * 是否电话确认 1:是  0: 否
     */
    @Column(name = "isConfirm")
    private Integer isconfirm;

    /**
     * 支付状态 :0到付1,待付款,2已付款,3待退款,4退款成功,5退款失败
     */
    @Column(name = "is_paiy")
    private Integer isPaiy;

    /**
     * 订单状态 0:提交订单 1:仓库配货 2:商品出库 3:等待收货 4:完成 5待退货 6已退货
     */
    @Column(name = "state")
    private Integer state;

    /**
     * 订单生成时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 附言
     */
    @Column(name = "note")
    private String note;

    /**
     * 用户Id
     */
    @Column(name = "buyer_id")
    private Long buyerId;

    /**
     * 获取ID
     *
     * @return id - ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置ID
     *
     * @param id ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取订单号
     *
     * @return oid - 订单号
     */
    public String getOid() {
        return oid;
    }

    /**
     * 设置订单号
     *
     * @param oid 订单号
     */
    public void setOid(String oid) {
        this.oid = oid;
    }

    /**
     * 获取运费
     *
     * @return deliver_fee - 运费
     */
    public BigDecimal getDeliverFee() {
        return deliverFee;
    }

    /**
     * 设置运费
     *
     * @param deliverFee 运费
     */
    public void setDeliverFee(BigDecimal deliverFee) {
        this.deliverFee = deliverFee;
    }

    /**
     * 获取应付金额
     *
     * @return payable_fee - 应付金额
     */
    public BigDecimal getPayableFee() {
        return payableFee;
    }

    /**
     * 设置应付金额
     *
     * @param payableFee 应付金额
     */
    public void setPayableFee(BigDecimal payableFee) {
        this.payableFee = payableFee;
    }

    /**
     * 获取订单金额
     *
     * @return total_price - 订单金额
     */
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    /**
     * 设置订单金额
     *
     * @param totalPrice 订单金额
     */
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * 获取支付方式 0:到付 1:在线 2:邮局 3:公司转帐
     *
     * @return payment_way - 支付方式 0:到付 1:在线 2:邮局 3:公司转帐
     */
    public Integer getPaymentWay() {
        return paymentWay;
    }

    /**
     * 设置支付方式 0:到付 1:在线 2:邮局 3:公司转帐
     *
     * @param paymentWay 支付方式 0:到付 1:在线 2:邮局 3:公司转帐
     */
    public void setPaymentWay(Integer paymentWay) {
        this.paymentWay = paymentWay;
    }

    /**
     * 获取货到付款方式.1现金,2POS刷卡
     *
     * @return payment_cash - 货到付款方式.1现金,2POS刷卡
     */
    public Integer getPaymentCash() {
        return paymentCash;
    }

    /**
     * 设置货到付款方式.1现金,2POS刷卡
     *
     * @param paymentCash 货到付款方式.1现金,2POS刷卡
     */
    public void setPaymentCash(Integer paymentCash) {
        this.paymentCash = paymentCash;
    }

    /**
     * 获取送货时间
     *
     * @return delivery - 送货时间
     */
    public Date getDelivery() {
        return delivery;
    }

    /**
     * 设置送货时间
     *
     * @param delivery 送货时间
     */
    public void setDelivery(Date delivery) {
        this.delivery = delivery;
    }

    /**
     * 获取是否电话确认 1:是  0: 否
     *
     * @return isConfirm - 是否电话确认 1:是  0: 否
     */
    public Integer getIsconfirm() {
        return isconfirm;
    }

    /**
     * 设置是否电话确认 1:是  0: 否
     *
     * @param isconfirm 是否电话确认 1:是  0: 否
     */
    public void setIsconfirm(Integer isconfirm) {
        this.isconfirm = isconfirm;
    }

    /**
     * 获取支付状态 :0到付1,待付款,2已付款,3待退款,4退款成功,5退款失败
     *
     * @return is_paiy - 支付状态 :0到付1,待付款,2已付款,3待退款,4退款成功,5退款失败
     */
    public Integer getIsPaiy() {
        return isPaiy;
    }

    /**
     * 设置支付状态 :0到付1,待付款,2已付款,3待退款,4退款成功,5退款失败
     *
     * @param isPaiy 支付状态 :0到付1,待付款,2已付款,3待退款,4退款成功,5退款失败
     */
    public void setIsPaiy(Integer isPaiy) {
        this.isPaiy = isPaiy;
    }

    /**
     * 获取订单状态 0:提交订单 1:仓库配货 2:商品出库 3:等待收货 4:完成 5待退货 6已退货
     *
     * @return state - 订单状态 0:提交订单 1:仓库配货 2:商品出库 3:等待收货 4:完成 5待退货 6已退货
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置订单状态 0:提交订单 1:仓库配货 2:商品出库 3:等待收货 4:完成 5待退货 6已退货
     *
     * @param state 订单状态 0:提交订单 1:仓库配货 2:商品出库 3:等待收货 4:完成 5待退货 6已退货
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 获取订单生成时间
     *
     * @return create_date - 订单生成时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置订单生成时间
     *
     * @param createDate 订单生成时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取附言
     *
     * @return note - 附言
     */
    public String getNote() {
        return note;
    }

    /**
     * 设置附言
     *
     * @param note 附言
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * 获取用户Id
     *
     * @return buyer_id - 用户Id
     */
    public Long getBuyerId() {
        return buyerId;
    }

    /**
     * 设置用户Id
     *
     * @param buyerId 用户Id
     */
    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }
}
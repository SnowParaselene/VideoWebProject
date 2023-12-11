/**
 * OrderRecords
 */
export type OrderRecord = {
    /**
     * 交易时间，订单创建时间
     */
    createTime: string;
    /**
     * 商品名称，所购买商品名称
     */
    goodsName: string;
    /**
     * 交易金额，订单总消费
     */
    orderAmount: string;
    /**
     * 订单状态，订单当前状态
     */
    orderStatus: string;
    /**
     * 订单类型，订单类型
     */
    orderType: string;
}
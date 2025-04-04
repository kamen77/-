package com.sky.service;

import com.sky.dto.*;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.vo.OrderPaymentVO;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.OrderSubmitVO;
import com.sky.vo.OrderVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface OrderService {

    /**
     * 用户下单
     * @param ordersSubmitDTO
     * @return
     */
    OrderSubmitVO submitOrder(OrdersSubmitDTO ordersSubmitDTO);

    /**
     * 订单支付
     * @param ordersPaymentDTO
     * @return
     */
    OrderPaymentVO payment(OrdersPaymentDTO ordersPaymentDTO);

    /**
     * 支付成功，修改订单状态
     * @param outTradeNo
     */
    void paySuccess(String outTradeNo);

    /**
     * 条件搜索订单
     * @param ordersPageQueryDTO
     * @return
     */
    PageResult conditionSearch(OrdersPageQueryDTO ordersPageQueryDTO);

    /**
     * 各个状态的订单数量统计
     * @return
     */
    OrderStatisticsVO statistics();

    /**
     * 订单详情
     * @param id
     * @return
     */
    OrderVO details(Long id);

    /**
     * 接单
     * @return
     */
    void confirm(OrdersConfirmDTO ordersConfirmDTO);

    /**
     * 拒单
     * @return
     */
    void rejection(OrdersRejectionDTO ordersRejectionDTO);

    /**
     * 取消订单
     * @return
     */
    void cancel(OrdersCancelDTO ordersCancelDTO);

    /**
     * 派送订单
     * @return
     */
    void delivery(Long id);

    /**
     * 完成订单
     * @return
     */
    void complete(Long id);

    /**
     * 历史订单查询
     * @param page
     * @param pageSize
     * @param status   订单状态 1待付款 2待接单 3已接单 4派送中 5已完成 6已取消
     * @return
     */
    PageResult pageQuery4User(int page, int pageSize, Integer status);

    /**
     * 用户取消订单
     * @return
     */
    void userCancelById(Long id);

    /**
     * 再来一单
     * @param id
     * @return
     */
    void repetition(Long id);

    /**
     * 客户催单
     */
    void reminder(long id);
}

package com.sky.task;


import com.sky.entity.Orders;
import com.sky.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
@EnableScheduling
public class OrderTask {

    @Autowired
    private OrderMapper orderMapper;


    /**
     * 处理超时订单的方法
     */
    @Scheduled(cron = "0 * * * * ? ") // 每分钟触发一次
    public void processTimeOrder(){
        log.info("定时处理超时订单：{}", LocalDateTime.now());

        LocalDateTime time = LocalDateTime.now().plusMinutes(-15);

        List<Orders> orderList = orderMapper.getByStatusAndOrderTimeLT(Orders.PENDING_PAYMENT,time);

        if(orderList != null && orderList.size() > 0){
            for (Orders orders : orderList) {
                orders.setStatus(Orders.CANCELLED);
                orders.setCancelReason("订单超时，自动取消");
                orders.setCancelTime(LocalDateTime.now());
                orderMapper.update(orders);
            }
        }

    }

    /**
     * 处理一直处于派送中的订单
     */
    @Scheduled(cron = "0 0 1 * * ?") // 每天凌晨一点
    public void processDelivery(){
        log.info("定时处理处于派送中的订单：{}",LocalDateTime.now());

        LocalDateTime time = LocalDateTime.now().plusMinutes(-60);

        List<Orders> orderList = orderMapper.getByStatusAndOrderTimeLT(Orders.DELIVERY_IN_PROGRESS, time);

        if(orderList != null && orderList.size() > 0){
            for (Orders orders : orderList) {
                orders.setStatus(Orders.COMPLETED);
                orderMapper.update(orders);
            }
        }
    }
}














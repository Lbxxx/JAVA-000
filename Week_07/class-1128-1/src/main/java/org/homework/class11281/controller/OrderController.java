package org.homework.class11281.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.homework.class11281.domain.Orders;
import org.homework.class11281.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;


    /**
     * 走主库
     * @return
     */
    @GetMapping("/update")
    public int update(){
        String text = "{\"buyerId\":1,\"createDate\":1606928960000,\"deliverFee\":1.00,\"delivery\":1607533753000,\"id\":1,\"isPaiy\":true,\"isconfirm\":true,\"note\":\"1\",\"oid\":\"1\",\"payableFee\":1.00,\"paymentCash\":true,\"paymentWay\":true,\"state\":true,\"totalPrice\":1.00}\n";
        Orders orders = JSONObject.parseObject(text, Orders.class);
        orders.setNote(String.valueOf(Math.random()*1000));
        return orderService.update(orders);
    }

    /**
     * 走从库
     * @return
     */
    @GetMapping("/select")
    public Orders select(Long id){
        Orders orders1 =  orderService.select(id);
        return orders1;
    }

}

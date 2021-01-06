package org.homework.class1228.controller;

import lombok.extern.log4j.Log4j2;
import org.homework.class1228.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @description TODO
 * author: liquan
 * date: 2020/12/31 18:51
 * version: 1.0
 */
@RestController
@Log4j2
public class TestStockController {

    @Autowired
    private StockService stockService;

    @RequestMapping(value = "/stock", method = RequestMethod.GET)
    public Object stock() {

        Long goodId = 1L;

        String redisKey = "redis_key:stock:" + goodId;

        String requestId = UUID.randomUUID().toString();

        Long stock = stockService.stock(redisKey, requestId, 1L, 3600L, () -> initStock(goodId));

        log.info("库存数量：" + stock);
        return stock > 0;
    }

    /**
     * 获取初始化库存
     *
     * @param goodId 商品ID
     * @return
     */
    private int initStock(Long goodId) {
        return 100;
    }

    @RequestMapping(value = "/getStock", method = RequestMethod.GET)
    public Object getStock() {

        Long goodId = 1L;

        String redisKey = "redis_key:stock:" + goodId;

        return stockService.getStock(redisKey);
    }
}

package org.homework.class11281.service;

import lombok.extern.slf4j.Slf4j;
import org.homework.class11281.config.ContextConst;
import org.homework.class11281.config.DataSourceSign;
import org.homework.class11281.domain.Orders;
import org.homework.class11281.mapper.OrdersMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class OrderService {

    @Resource
    private OrdersMapper ordersMapper;

    @DataSourceSign(ContextConst.DataSourceType.MASTER)
    public int update(Orders orders) {
        return ordersMapper.updateByPrimaryKey(orders);
    }

    @DataSourceSign(ContextConst.DataSourceType.SLAVE)
    public Orders select(Long id) {
        Orders orders1 = ordersMapper.selectByPrimaryKey(id);
        return orders1;
    }
}

package org.homework.class11282;

import com.alibaba.fastjson.JSONObject;
import org.homework.class11282.domain.Orders;
import org.homework.class11282.mapper.OrdersMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
class Class11282ApplicationTests {

	@Resource
	private OrdersMapper ordersMapper;

	/**
	 * 直接查找走从库
	 */
	@Test
	void select() {
		Orders orders = ordersMapper.selectByPrimaryKey(1);
		System.out.println(orders);
	}

	/**
	 * 插入后再查找走主库
	 */
	@Test
	void insert() {
		String text = "{\"buyerId\":1,\"createDate\":1606928960000,\"deliverFee\":1.00,\"delivery\":1607533753000,\"isPaiy\":true,\"isconfirm\":true,\"note\":\"1\",\"oid\":\"1\",\"payableFee\":1.00,\"paymentCash\":true,\"paymentWay\":true,\"state\":true,\"totalPrice\":1.00}\n";
		Orders orders = JSONObject.parseObject(text, Orders.class);
		orders.setNote(String.valueOf(Math.random()*1000));
		ordersMapper.insertSelective(orders);
		System.out.println(orders);
	}

}

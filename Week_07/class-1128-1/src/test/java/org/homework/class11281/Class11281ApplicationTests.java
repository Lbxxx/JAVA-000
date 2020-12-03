package org.homework.class11281;

import com.alibaba.fastjson.JSON;
import org.homework.class11281.domain.Orders;
import org.homework.class11281.mapper.OrdersMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
class Class11281ApplicationTests {

	@Resource
	private OrdersMapper ordersMapper;

	@Test
	void contextLoads() {

		Orders orders1 = ordersMapper.selectByPrimaryKey(1L);

		System.out.println(JSON.toJSONString(orders1));

	}

}

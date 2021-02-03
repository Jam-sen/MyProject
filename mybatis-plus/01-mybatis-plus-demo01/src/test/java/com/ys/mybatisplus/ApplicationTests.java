package com.ys.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ys.mybatisplus.domain.User;
import com.ys.mybatisplus.mapper.UserMapper;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.UnknownServiceException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class ApplicationTests {

	@Autowired
	private UserMapper userMapper;

	@Test
	void update() {
		User user = new User();
		user.setId(1L);
		user.setAge(30);
		System.out.println(userMapper.updateById(user));
	}

	@Test
	void add() {
		User user = new User();
		user.setAge(21);
		user.setName("张三");
		user.setEmail("11111@qq.com");
		int num = userMapper.insert(user);
		System.out.println("增加数据条数："+num);
		System.out.println("id属性已经被MybatisPlus自动生成了："+user);
	}

	//无脑查所有：select * from user
	@Test
	void selectAll() {
		List<User> users = userMapper.selectList(null);
		users.forEach(System.out::println);
	}

	//测试mybatisPlus乐观锁
	@Test
	public void testOptimisticLocker() {
		User user = userMapper.selectById(1356461544904351745L);
		user.setAge(12);
		userMapper.updateById(user);
	}

	//根据Id查单条
	@Test
	public void testSelectId() {
		User user = userMapper.selectById(2);
		System.out.println(user);
	}

	//根据多个id查多条
	@Test
	public void testSelectBatchIds() {
		List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3, 4));
		for (User user : users) {
			System.out.println(user);
		}
	}

	//根据多个条件进行查询
	@Test
	public void testSelectByMap() {
		Map<String, Object> map = new HashMap<>();
		map.put("name", "jone");
		map.put("age", 12);
		List<User> users = userMapper.selectByMap(map);
		users.forEach(System.out::println);
	}


	//分页查询
	@Test
	public void testSelectPage() {
		Page<User> page = new Page<>(2,3);
		Page<User> userPage = userMapper.selectPage(page, null);
		System.out.println("总条数：" + userPage.getTotal());
		System.out.println("每页条数：" + userPage.getSize());
		System.out.println("current:"+userPage.getCurrent());
		System.out.println(userPage.getRecords());
	}

	//根据id删除单条
	@Test
	public void testDeleteById() {
		int num = userMapper.deleteById(1);
		System.out.println("删除数量"+num);
	}

	//根据多个id删除多条
	@Test
	public void testDeleteBatchIds() {
		int num = userMapper.deleteBatchIds(Arrays.asList(1, 2, 3));
		System.out.println("删除数量" + num);
	}

	//逻辑删除
	@Test
	public void testLogicDelete() {
		int i = userMapper.deleteById(2);
		System.out.println("删除条数"+i);
	}

	//查询被逻辑删除的数据
	@Test
	public void testSelectDeleted(int x,int y) {
		List<User> users = userMapper.selectDeleted();
	}

	@Test
	//复杂条件查询
	public void testSelect() {
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.eq("name", "Tom");
		List<User> users = userMapper.selectList(wrapper);
		users.forEach(System.out::println);
	}
}

package com.seele.Redis;

import java.util.List;

import redis.clients.jedis.Jedis;

public class RedisDemo {

	public static void main(String[] args) {
		  Jedis jedis = new Jedis("localhost",6379);
	      System.out.println("Connection to server sucessfully");
	      //查看服务是否运行
	      System.out.println("Server is running: "+jedis.ping());
	      
/*	      jedis.set("foo", "bar");
	      String value = jedis.get("foo");
	      System.out.println(value);*/
	      
	      //存储数据到列表中
	      jedis.lpush("tutorial-list", "Redis");
	      jedis.lpush("tutorial-list", "Mongodb");
	      jedis.lpush("tutorial-list", "Mysql");
	      // 获取存储的数据并输出
	      List<String> list = jedis.lrange("tutorial-list", 0 ,-1);
	      for(int i=0; i<list.size(); i++) {
	        System.out.println("Stored string in redis:: "+list.get(i));
	      }
	}

}

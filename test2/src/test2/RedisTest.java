package test2;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class RedisTest {

	public static void main(String[] args) {

		test5();

	}

	public static void test1() {
		Jedis jedis = new Jedis("localhost");
		// jedis.set("runoobkey", "www.runoob.com");
		String r = jedis.get("runoobkey");
		System.out.println(r);
		jedis.close();
	}

	public static void test2() {
		Jedis jedis = new Jedis("localhost");
		// 存储数据到列表中
//		jedis.lpush("site-list", "Runoob");
//		jedis.lpush("site-list", "Google");
//		jedis.lpush("site-list", "Taobao");
//		jedis.lpush("site-list", "Jingdong");
//		jedis.lpushx("site-list", "user:mm","age:18");
		// 获取存储的数据并输出
		List<String> list = jedis.lrange("site-list", 0, -1);
		for (int i = 0; i < list.size(); i++) {
			System.out.println("列表项为: " + list.get(i));
		}
		jedis.close();
	}
	
	public static void test4() {
		Jedis jedis = new Jedis("localhost");
		String key = "test:hash:a";
        // 为了保持多次测试结果一样，每次开始时都删除
        jedis.del(key);
		Map<String, String> values = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            String s = String.valueOf((char) (i + 'A'));
            values.put(s, s + "_val");
        }
        Iterator<Entry<String, String>> ite = values.entrySet().iterator();
        while (ite.hasNext()) {
            Entry<String, String> entry = ite.next();
            String k = entry.getKey();
            String v = entry.getValue();
            jedis.hset(key, k, v);
        }
        Map<String, String> val = jedis.hgetAll(key);
        System.out.println(val);
        jedis.close();
	}
	
	public static void test5() {
		Jedis jedis = new Jedis("localhost");
		Map<String, String> values = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            String s = String.valueOf((char) (i + 'A'));
            values.put(s, s + "_val");
        }
        jedis.lpush("stus1", values.toString());
        Map<String, String> values1 = new HashMap<>();
        for (int i = 10; i < 20; i++) {
            String s = String.valueOf((char) (i + 'A'));
            values1.put(s, s + "_val");
        }
        jedis.lpush("stus1", values1.toString());
        List<String> list = jedis.lrange("stus1", 0, -1);
        for (int i = 0; i < list.size(); i++) {
			System.out.println("s: " + list.get(i));
		}
        jedis.close();
	}

	//输出所有保存的key
	public static void test3() {
		// 连接本地的 Redis 服务
		Jedis jedis = new Jedis("localhost");

		// 获取数据并输出
		Set<String> keys = jedis.keys("*");
		Iterator<String> it = keys.iterator();
		while (it.hasNext()) {
			String key = it.next();
			System.out.println(key);
		}
		jedis.close();
	}
}

class Student{
	private int sid;
	private String sName;
	private String sAddress;
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	public String getsAddress() {
		return sAddress;
	}
	public void setsAddress(String sAddress) {
		this.sAddress = sAddress;
	}
	public Student(int sid, String sName, String sAddress) {
		super();
		this.sid = sid;
		this.sName = sName;
		this.sAddress = sAddress;
	}
	public Student() {
		super();
	}
	
}

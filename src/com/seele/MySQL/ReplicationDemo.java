package com.seele.MySQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Properties;
 

import com.mysql.jdbc.ReplicationDriver;

//主从读写分离

public class ReplicationDemo {
 
  public static void main(String[] args) throws Exception {
    ReplicationDriver driver = new ReplicationDriver();
 
    Properties props = new Properties();
    // 对Slave进行故障恢复
    // We want this for failover on the slaves
    props.put("autoReconnect", "true");
    // 在Slave上进行负载均衡
    // We want to load balance between the slaves
    props.put("roundRobinLoadBalance", "true");
 
    props.put("user", "root");
    props.put("password", "123456");
 
    //
    // Looks like a normal MySQL JDBC url, with a
    // comma-separated list of hosts, the first
    // being the 'master', the rest being any number
    // of slaves that the driver will load balance against
    //
 
    Connection conn =
        driver.connect("jdbc:mysql:replication://192.168.0.100:3306,192.168.0.100:3307/test",
            props);
 
    //
    // Perform read/write work on the master
    // by setting the read-only flag to "false"
    //
 
    conn.setReadOnly(false);
    conn.setAutoCommit(false);
    conn.createStatement().executeUpdate("insert into test.user (username) values ('homurachyan')");
    conn.commit();
 
    //
    // Now, do a query from a slave, the driver automatically picks one
    // from the list
    //
 
    conn.setReadOnly(true);
 
    ResultSet rs =
      conn.createStatement().executeQuery("SELECT username FROM test.user");
    
     //.......
    while(rs.next()){
    	System.out.println("username : " + rs.getString(1));
    }
    conn.close();
  }
}

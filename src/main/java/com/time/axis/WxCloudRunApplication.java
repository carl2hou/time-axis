package com.time.axis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author carl
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.time.axis.dao"})
public class WxCloudRunApplication {  

  public static void main(String[] args) {
    // 泳道
    SpringApplication.run(WxCloudRunApplication.class, args);
  }
}

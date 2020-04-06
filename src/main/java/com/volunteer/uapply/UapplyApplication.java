package com.volunteer.uapply;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


/**
 * @author GuoShuSong
 */
@MapperScan("com.volunteer.uapply.mapper")
@SpringBootApplication
public class UapplyApplication {

    public static void main(String[] args) {
        SpringApplication.run(UapplyApplication.class, args);
    }

}

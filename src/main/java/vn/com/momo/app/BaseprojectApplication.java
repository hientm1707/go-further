package vn.com.momo.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BaseprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseprojectApplication.class, args);
        System.out.println("Application is ready");
    }

}
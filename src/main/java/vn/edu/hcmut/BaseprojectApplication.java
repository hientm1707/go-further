package vn.edu.hcmut;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {MongoAutoConfiguration.class})
public class BaseprojectApplication {
    @Value("${MONGO_CLOUD_URI}")
    static String uri;

    public static void main(String[] args) {
        SpringApplication.run(BaseprojectApplication.class, args);
        System.out.println(System.getenv().get("MONGO_CLOUD_URI"));
        System.out.println("Application is ready");
    }

}
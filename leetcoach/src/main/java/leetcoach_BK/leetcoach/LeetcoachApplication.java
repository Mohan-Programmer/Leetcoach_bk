package leetcoach_BK.leetcoach;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
		org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class
})
public class LeetcoachApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeetcoachApplication.class, args);
	}

}

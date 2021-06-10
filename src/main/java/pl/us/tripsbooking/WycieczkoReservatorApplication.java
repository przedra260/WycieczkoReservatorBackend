package pl.us.tripsbooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableScheduling
@EnableSwagger2
public class WycieczkoReservatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(WycieczkoReservatorApplication.class, args);
	}

	@Bean
	public Docket get() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/**"))
				.apis(RequestHandlerSelectors.basePackage("pl.us.tripsbooking"))
				.build().apiInfo(createApiInfo());
	}

	private ApiInfo createApiInfo() {
		return new ApiInfo("WycieczkoResevator API",
				"This is a communication API of WycieczkoReservator, allowing flawless communication between Spring backend and Angular frontend.",
				"1.00",
				"http://localhost:2507/swagger-ui.html",
				new Contact("Student", "http://localhost:2507/swagger-ui.html", "email@gmail.com"),
				"my own license",
				"localhost:2507",
				Collections.emptyList());
	}

}

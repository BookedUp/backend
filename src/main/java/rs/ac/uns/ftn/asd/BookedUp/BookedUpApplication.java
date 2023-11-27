package rs.ac.uns.ftn.asd.BookedUp;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookedUpApplication {

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
	public static void main(String[] args) {

		SpringApplication.run(BookedUpApplication.class, args);
		System.out.println("Test crud-functionality");

	}

}

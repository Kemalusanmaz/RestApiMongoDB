package com.RestApi.MongoDB;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@SpringBootApplication
public class MongoDbApplication {

	public static void main(String[] args) {

		SpringApplication.run(MongoDbApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(IStudentRepository repository, MongoTemplate mongoTemplate) { //Springboot framework tarafından sağlanır. uygulama başlatıldığında belirli kod ğarçacıklarını çalıştırmaya yarar.
		return args -> { // ... uygulama başlatıldığında çalıştırılacak kodlar ...

			Address address = new Address( //Address clasının referansı oluşturulur.
					"Turkey",
					"İstanbul",
					"34744"
			);

			Student student = new Student( //Student Entity classının referansı oluşturulur.
					"Kemal",
					"Usanmaz",
					"kemal@gmail.com",
					Gender.MALE,
					address,
					List.of("Computer Science","Math"),
					BigDecimal.TEN,
					LocalDateTime.now()

			);

			//usingMongoTemplateAndQuery(repository, mongoTemplate, student);
			String email = "kemal@gmail.com";
			repository.findStudentByEmail(email).ifPresentOrElse(student1 -> {
				System.out.println(student1 + "already exist");
			},()->{System.out.println("Inserting student: " + student);
				repository.insert(student);});

		};
	}

	private static void usingMongoTemplateAndQuery(IStudentRepository repository, MongoTemplate mongoTemplate, Student student) {
		String email = "kemal@gmail.com";
		Query query = new Query();
		query.addCriteria(Criteria.where("email").is(email));

		List<Student> students = mongoTemplate.find(query, Student.class);

		if(students.size()>1){
			throw new IllegalStateException("found many students with email: " + email);
		}

		if(students.isEmpty()){
			System.out.println("Inserting student: " + student);
			repository.insert(student); //DAL classında extend edilen MongoRepository arayüzüyle CRUD işlemi.
		}else {
			System.out.println(student + "already exist");
		}
	}


}


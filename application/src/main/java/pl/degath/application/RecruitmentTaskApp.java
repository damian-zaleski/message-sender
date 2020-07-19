package pl.degath.application;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import pl.degath.message.port.MessageRepositoryImpl;

@SpringBootApplication
public class RecruitmentTaskApp {

    public static void main(String[] args) {
        SpringApplication.run(RecruitmentTaskApp.class, args);
    }
}

package tn.esprit.skillservice;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tn.esprit.skillservice.entities.Answer;
import tn.esprit.skillservice.entities.Question;
import tn.esprit.skillservice.entities.Skill;
import tn.esprit.skillservice.services.AnswerService;
import tn.esprit.skillservice.services.QuestionService;
import tn.esprit.skillservice.services.QuizService;
import tn.esprit.skillservice.services.SkillService;

@SpringBootApplication
@EnableFeignClients
public class SkillServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkillServiceApplication.class, args);
	}


	@Autowired
	private SkillService skillService;

	@Autowired
	private QuestionService questionService;

	@Autowired
	private AnswerService answerService;

	@Autowired
	private QuizService quizService;

	@Bean
	InitializingBean initSkillTable(){
		return ()->{
			// Skills
			Skill skill= skillService.addSkill(new Skill("JavaScript"));
			skillService.addSkill(new Skill("Java"));
			skillService.addSkill(new Skill("PHP"));

			//Q1
			Question question= questionService.addQuestion(new Question("Good question", skill));
			answerService.addAnswer(new Answer("True", true, question));
			answerService.addAnswer(new Answer("False", false, question));

			//Q2
			question= questionService.addQuestion(new Question("Another one", skill));
			answerService.addAnswer(new Answer("True", true, question));
			answerService.addAnswer(new Answer("False", false, question));
			answerService.addAnswer(new Answer("False", false, question));

			//Q3
			question= questionService.addQuestion(new Question("Newwwww", skill));
			answerService.addAnswer(new Answer("True", true, question));
			answerService.addAnswer(new Answer("False", false, question));
			answerService.addAnswer(new Answer("False", false, question));

			//Q4
			question= questionService.addQuestion(new Question("Nah", skill));
			answerService.addAnswer(new Answer("True", true, question));
			answerService.addAnswer(new Answer("False", false, question));
			answerService.addAnswer(new Answer("False", false, question));

			//Q5
			question= questionService.addQuestion(new Question("Q5", skill));
			answerService.addAnswer(new Answer("True", true, question));
			answerService.addAnswer(new Answer("False", false, question));
			answerService.addAnswer(new Answer("False", false, question));

		};
	}

	/*
	@Bean
	public static WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:5000")
						.allowedMethods("GET", "POST", "PUT", "DELETE")
						.allowedHeaders("*");
			}
		};
	}*/
}

package com.my;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@EnableBatchProcessing
public class Hw2t2Configuration {
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	@Bean
	public UserItemReaderInMemory reader() {
		return new UserItemReaderInMemory();
	}
	
	@Bean
	public UserItemReaderJdbc readerJdbc() {
		return new UserItemReaderJdbc(jdbcTemplate);
	}
	
	@Bean
	public UserItemProcessor processor() {
		return new UserItemProcessor();
	}
	
	@Bean
	public UserItemWriterSender writer() {
		return new UserItemWriterSender();
	}
	
	@Bean
	public Job sendEmailsToUser(JobCompletionNotificationListener listener, Step step1) {
		return jobBuilderFactory.get("sendEmailsToUser")
				.listener(listener)
				.flow(step1)
				.end()
				.build();
	}
	
	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1")
				.<User, User> chunk(2)
				.reader(
//						reader()
						readerJdbc()
						)
				.processor(processor())
				.writer(writer())
				.build();
	}

}

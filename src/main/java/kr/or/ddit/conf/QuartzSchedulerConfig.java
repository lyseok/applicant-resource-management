package kr.or.ddit.conf;

import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

@Configuration
public class QuartzSchedulerConfig {

//	@Bean
//	public SpringBeanJobFactory springBeanJobFactory() {
//		return new SpringBeanJobFactory();
//	}
//	
//	@Bean
//	public SchedulerFactoryBean scheduler(SpringBeanJobFactory jobFactory, Trigger...triggers) {
//		SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
//		factoryBean.setJobFactory(jobFactory);
//		factoryBean.setTriggers(triggers);
//		return factoryBean;
//	}
}

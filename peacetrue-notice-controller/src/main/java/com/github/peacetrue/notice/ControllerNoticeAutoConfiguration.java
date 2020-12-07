package com.github.peacetrue.notice;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


/**
 * @author xiayx
 */
@Configuration
@EnableConfigurationProperties(ControllerNoticeProperties.class)
@ComponentScan(basePackageClasses = ControllerNoticeAutoConfiguration.class)
@PropertySource("classpath:/application-notice-controller.yml")
public class ControllerNoticeAutoConfiguration {

}

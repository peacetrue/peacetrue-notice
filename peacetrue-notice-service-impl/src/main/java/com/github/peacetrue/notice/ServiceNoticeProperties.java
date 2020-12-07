package com.github.peacetrue.notice;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author xiayx
 */
@Data
@ConfigurationProperties(prefix = "peacetrue.notice")
public class ServiceNoticeProperties {

}

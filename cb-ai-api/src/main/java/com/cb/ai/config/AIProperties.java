package com.cb.ai.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @Description:
 * @Author: ARPHS
 * @Date: 2024-10-25 11:20
 * @Version: 1.0
 **/

@Data
@Configuration
@ConfigurationProperties("ai")
public class AIProperties {

    private String baseUrl;

    private Map<String,String>  assistantAppIds;//助手app

    private Map<String,String> skillAppIds;//技能

}

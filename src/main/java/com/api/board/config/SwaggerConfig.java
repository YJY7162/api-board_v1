package com.api.board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;
import java.util.Set;
 
@EnableSwagger2
@Configuration
public class SwaggerConfig extends WebMvcConfigurationSupport {
 
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).consumes(getConsumeContentTypes())
        											  .produces(getProduceContentTypes())
        											  .apiInfo(getApiInfo())
        											  .select()
        											  .apis(RequestHandlerSelectors
                                                              .basePackage("com.api.board.controller"))
        											  .paths(PathSelectors.ant("/board/**"))
        											  .build();
    }
 
    private Set<String> getConsumeContentTypes() {
        Set<String> consumes = new HashSet<>();
        consumes.add("application/json;charset=UTF-8");
        consumes.add("application/x-www-form-urlencoded");
        return consumes;
    }
 
    private Set<String> getProduceContentTypes() {
        Set<String> produces = new HashSet<>();
        produces.add("application/json;charset=UTF-8");
        return produces;
    }
 
    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder().title("REST API 게시판 만들기")
        						   .description("게시판 관련한 API")
        						   .version("1.0")
        						   .build();
    }
 
    /** 404 Not Found가 발생하는 경우 swagger-ui.html 위치를 추가 */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html**")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}

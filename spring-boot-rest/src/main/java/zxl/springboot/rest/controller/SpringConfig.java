package zxl.springboot.rest.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Created by xiao on 2018/8/27.
 */
@Configuration
@EnableWebMvc

public class SpringConfig implements WebMvcConfigurer {

//    @Bean
//    public HttpMessageConverter<String> responseBodyStringConverter() {
//        StringHttpMessageConverter converter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
//        return converter;
//    }
//
//    /**
//     * 修改StringHttpMessageConverter默认配置
//     * @param converters
//     */
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters){
//        converters.add(responseBodyStringConverter());
//    }

}

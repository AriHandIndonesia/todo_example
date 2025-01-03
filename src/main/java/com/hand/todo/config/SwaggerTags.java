package com.hand.todo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.Tag;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger Api 描述配置
 */
@Configuration
public class SwaggerTags {

    public static final String EXAMPLE = "Example";
    public static final String TASK = "Task";
    public static final String USER = "User";
    public static final String MESSAGE = "Message";
    public static final String FILE = "File";

    @Autowired
    public SwaggerTags(Docket docket) {
        docket.tags(
                new Tag(EXAMPLE, "EXAMPLE 案例")
        );
        docket.tags(new Tag(TASK,"task"));
        docket.tags(new Tag(USER,"user"));
        docket.tags(new Tag(MESSAGE,"message"));
        docket.tags(new Tag(FILE,"File IO"));
    }
}

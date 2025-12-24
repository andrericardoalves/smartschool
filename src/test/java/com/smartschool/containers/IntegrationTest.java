package com.smartschool.containers;

import com.smartschool.SmartschoolApplication;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureWebMvc;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@AutoConfigureWebMvc
@AutoConfigureMockMvc
@ImportAutoConfiguration
@SpringBootTest(classes = SmartschoolApplication.class)
@ExtendWith({PostgresTestContainerConfig.class})
public @interface IntegrationTest {
}

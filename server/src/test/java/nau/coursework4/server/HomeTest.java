package nau.coursework4.server;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

    @SpringBootTest
    class HomeTest {

        @Autowired
        private CourseworkApplication controller;

        @Test
        void contextLoads() throws Exception {
            assertThat(controller).isNotNull();
        }
    }
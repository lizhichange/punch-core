package com.fulihui.punch.web.controller;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.fulihui.punch.web.SpringApplicationBootstrap;

/**
 * @author lz 2017/1/7 0007.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringApplicationBootstrap.class)
@WebAppConfiguration
public class ControllerTest {
    private final transient Logger logger      = LoggerFactory.getLogger(getClass());

    private MediaType              contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
        MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    private MockMvc                mockMvc;

    private String                 userName    = "bdussault";

    private HttpMessageConverter   mappingJackson2HttpMessageConverter;

    @Autowired
    private WebApplicationContext  webApplicationContext;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
            .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny()
            .orElse(null);

    }

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

    }

    @Test
    public void userNotFound() throws Exception {

    }

    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON,
            mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}

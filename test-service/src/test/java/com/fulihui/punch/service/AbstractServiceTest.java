package com.fulihui.punch.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 * @author Created by Willard.Hu on 2016/8/3 0003.
 */
@ContextConfiguration(locations = {"classpath:/META-INF/spring/root-context.xml",
        "classpath:/META-INF/spring/dubbo-consumer.xml"})
public abstract class AbstractServiceTest extends AbstractTestNGSpringContextTests {
    protected final transient Logger log = LoggerFactory.getLogger(getClass());
}

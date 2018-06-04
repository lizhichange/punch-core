package com.fulihui.punch;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * 对spirng支持的单元测试
 * Created by Willard.Hu on 2016/3/5.
 */
public abstract class AbstractToStringSupport extends AbstractJUnit4SpringContextTests {
    protected final transient Logger LOGGER = LoggerFactory.getLogger(getClass());

    protected static String toString(Object o) {
        return ToStringBuilder.reflectionToString(o, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}

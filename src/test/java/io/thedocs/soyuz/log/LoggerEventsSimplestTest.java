package io.thedocs.soyuz.log;

import io.thedocs.soyuz.to;
import org.junit.Test;

/**
 * Created on 14.04.17.
 */
public class LoggerEventsSimplestTest {

    private static final LoggerEvents loge = LoggerEvents.getInstance(LoggerEventsSimplestTest.class);

    @Test
    public void main() {
        loge.info("world.hello", to.map("project", "soyuz-loge")); //world.hello: {project=soyuz-loge}
    }

}

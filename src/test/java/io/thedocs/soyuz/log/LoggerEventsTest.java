package io.thedocs.soyuz.log;

import io.belov.soyuz.utils.to;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.fail;

/**
 * Created on 14.04.17.
 */
public class LoggerEventsTest {

    private static final LoggerEvents loge = LoggerEvents.getInstance(LoggerEventsTest.class);

    @Test
    public void shouldCorrectlyLogEvents() {
        //setup
        Exception e = new IllegalStateException();

        //when
        loge.warn("user.register.e.notUnique", to.map("mail", "hello@gmail.com"));
        loge.debug("user.login", to.map("mail", "hello@gmail.com"));
        loge.info("task.created", to.map("user", "hello@gmail.com", "label", "Improve documentation", "type", "todo"));
        loge.error("task.process.e", to.map("id", 123, "type", "todo"), e);

        //then
        assertLogs(to.list(
                "WARN  i.thedocs.soyuz.log.LoggerEventsTest - user.register.e.notUnique: {mail=hello@gmail.com}",
                "DEBUG i.thedocs.soyuz.log.LoggerEventsTest - user.login: {mail=hello@gmail.com}",
                "INFO  i.thedocs.soyuz.log.LoggerEventsTest - task.created: {label=Improve documentation, type=todo, user=hello@gmail.com}",
                "ERROR i.thedocs.soyuz.log.LoggerEventsTest - task.process.e: {id=123, type=todo}"
        ));
    }

    private void assertLogs(List<String> logsExpected) {
        List<String> logs = getLogContent();

        for (String logExpected : logsExpected) {
            assertContainLogEntry(logs, logExpected);
        }
    }

    private void assertContainLogEntry(List<String> logs, String logExpected) {
        for (String log : logs) {
            if (log.contains(logExpected)) return;
        }

        fail("Logs do not contain " + logExpected);
    }

    private List<String> getLogContent() {
        try {
            return FileUtils.readLines(new File(System.getProperty("java.io.tmpdir"), "loge-test.log"), "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
package io.thedocs.soyuz.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import java.util.HashMap;
import java.util.Map;

/**
 * slf4j wrapper to split events from params
 */
public class LoggerEvents {

    private static final Object[] EMPTY_PARAMS = new Object[]{};

    /**
     * @param logger to be wrapped by io.thedocs.soyuz.log.LoggerEvents
     * @return created io.thedocs.soyuz.log.LoggerEvents
     */
    public static LoggerEvents getInstance(Logger logger) {
        return new LoggerEvents(logger);
    }

    /**
     * @param clazz used as param to create org.slf4j.Logger
     * @return created io.thedocs.soyuz.log.LoggerEvents
     */
    public static LoggerEvents getInstance(Class clazz) {
        return new LoggerEvents(LoggerFactory.getLogger(clazz));
    }

    private Logger log;

    private LoggerEvents(Logger logger) {
        this.log = logger;
    }

    public Logger getLog() {
        return log;
    }

    public void log(Level level, String event) {
        log(level, event, null);
    }

    public void log(Level level, String event, Map params) {
        log(level, event, null, params);
    }

    public void log(Level level, String event, Map debugContext, Map params) {
        switch (level) {
            case TRACE:
                trace(event, debugContext, params);
                break;
            case DEBUG:
                debug(event, debugContext, params);
                break;
            case INFO:
                info(event, debugContext, params);
                break;
            case WARN:
                warn(event, debugContext, params);
                break;
            case ERROR:
                error(event, debugContext, params);
                break;
            default:
                throw new IllegalStateException("Unknown log level " + level);
        }
    }

    public void trace(String event) {
        trace(event, null);
    }

    public void trace(String event, Map params) {
        log.trace(toLogMessage(event, params), toLogParams(params));
    }

    public void trace(String event, Map debugContext, Map params) {
        trace(event, joinContexts(debugContext, params));
    }

    public void debug(String event) {
        debug(event, null);
    }

    public void debug(String event, Map params) {
        log.debug(toLogMessage(event, params), toLogParams(params));
    }

    public void debug(String event, Map debugContext, Map params) {
        debug(event, joinContexts(debugContext, params));
    }

    public void info(String event) {
        info(event, null);
    }

    public void info(String event, Map params) {
        log.info(toLogMessage(event, params), toLogParams(params));
    }

    public void info(String event, Map debugContext, Map params) {
        info(event, joinContexts(debugContext, params));
    }

    public void warn(String event) {
        warn(event, (Map) null);
    }

    public void warn(String event, Throwable throwable) {
        log.warn(event, throwable);
    }

    public void warn(String event, Map params) {
        log.warn(toLogMessage(event, params), toLogParams(params));
    }

    public void warn(String event, Map debugContext, Map params) {
        warn(event, joinContexts(debugContext, params));
    }

    public void warn(String event, Map params, Throwable throwable) {
        log.warn(event + ": " + params.toString(), throwable);
    }

    public void error(String event) {
        error(event, (Map) null);
    }

    public void error(String event, Throwable throwable) {
        log.error(event, throwable);
    }

    public void error(String event, Map params) {
        log.error(toLogMessage(event, params), toLogParams(params));
    }

    public void error(String event, Map debugContext, Map params) {
        error(event, joinContexts(debugContext, params));
    }

    public void error(String event, Map params, Throwable throwable) {
        log.error(event + ": " + params.toString(), throwable);
    }

    public boolean isTraceEnabled() {
        return log.isTraceEnabled();
    }

    public boolean isDebugEnabled() {
        return log.isDebugEnabled();
    }

    public boolean isInfoEnabled() {
        return log.isInfoEnabled();
    }

    public boolean isWarnEnabled() {
        return log.isWarnEnabled();
    }

    public boolean isErrorEnabled() {
        return log.isErrorEnabled();
    }

    private String toLogMessage(String event, Map params) {
        if (params == null) {
            return event;
        } else {
            return event + ": {}";
        }
    }

    private Object[] toLogParams(Map params) {
        if (params == null) {
            return EMPTY_PARAMS;
        } else {
            return new Object[]{params};
        }
    }

    private Map joinContexts(Map debugContext, Map params) {
        if (debugContext == null && params == null) {
            return null;
        } else {
            Map answer = (debugContext == null) ? new HashMap() : new HashMap(debugContext);

            if (params != null) {
                answer.putAll(params);
            }

            return answer;
        }
    }

}

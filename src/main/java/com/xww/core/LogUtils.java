package com.xww.core;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Slf4j
public class LogUtils {
    private static boolean isDev = false;

    private LogUtils() {
    }

    public static void setIsDev(boolean b) {
        isDev = b;
    }

    public boolean isDev() {
        return isDev;
    }

    /**
     * 记录 INFO 级别日志（带上下文信息）
     *
     * @param clazz   调用日志的类（自动获取当前类名，可不传）
     * @param message 日志消息模板（支持 {} 占位符）
     * @param args    消息模板的参数（可变参数）
     */
    public static void info(Class<?> clazz, String message, Object... args) {
        if (!isDev) {
            return;
        }
        Logger logger = LoggerFactory.getLogger(clazz);
        if (logger.isInfoEnabled()) {
            String logMsg = formatMessage(message, args);
            logger.info("[{}] {}", getCallerInfo(), logMsg);
        }
    }

    public static void warn(Class<?> clazz, String message, Object... args) {
        if (!isDev) {
            return;
        }
        Logger logger = LoggerFactory.getLogger(clazz);
        if (logger.isWarnEnabled()) {
            String logMsg = formatMessage(message, args);
            logger.warn("[{}] {}", getCallerInfo(), logMsg);
        }
    }

    public static void debug(Class<?> clazz, String message, Object... args) {
        if (!isDev) {
            return;
        }
        Logger logger = LoggerFactory.getLogger(clazz);
        if (logger.isDebugEnabled()) {
            String logMsg = formatMessage(message, args);
            logger.debug("[{}] {}", getCallerInfo(), logMsg);
        }
    }

    public static void error(Class<?> clazz, String message, Throwable e, Object... args) {
        if (!isDev) {
            return;
        }
        Logger logger = LoggerFactory.getLogger(clazz);
        if (logger.isErrorEnabled()) {
            String logMsg = formatMessage(message, args);
            logger.error("[{}] {} | 异常信息: {}", getCallerInfo(), logMsg, e.getMessage(), e);
        }
    }

    private static String formatMessage(String message, Object... args) {
        if (args == null || args.length == 0) {
            return message;
        }
        try {
            return String.format(message, args);
        } catch (Exception e) {
            // 防止格式化异常导致日志丢失
            return message + " [参数格式化失败]";
        }
    }

    private static String getCallerInfo() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        for (int i = 3; i < stackTrace.length; i++) {
            StackTraceElement element = stackTrace[i];
            String className = element.getClassName();
            String methodName = element.getMethodName();
            int lineNumber = element.getLineNumber();
            if (!className.startsWith("java.lang") && !className.startsWith("org.slf4j")) {
                return String.format("[%s#%s:%d]", className, methodName, lineNumber);
            }
        }
        return "[UnknownCaller]";
    }
}

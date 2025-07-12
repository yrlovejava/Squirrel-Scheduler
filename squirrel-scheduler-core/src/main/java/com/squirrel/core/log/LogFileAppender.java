package com.squirrel.core.log;

import com.squirrel.core.config.LogFileConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * store task execute log file
 *
 * @author xiaobai
 * @version 1.0.0
 * @since 2025/6/25 下午7:53
 */
public class LogFileAppender {

    private static Logger logger = LoggerFactory.getLogger(LogFileAppender.class);

    public static final String DEFAULT_LOG_BASE_PATH = "log/squirrel-schedule/task";
    private static String logPath;

    public LogFileAppender(LogFileConfig config) {
        logPath = config.getPath() != null ? config.getPath() : DEFAULT_LOG_BASE_PATH;
        initLogPath();
    }

    public static void initLogPath() {
        File logPathDir = new File(logPath);
        if (!logPathDir.exists()) {
            if (!logPathDir.mkdirs()) {
                logger.error("Failed to create log directory: {}", logPath);
            }
        }
        logPath = logPathDir.getPath();
    }

    public static String getLogPath() {
        return logPath;
    }

    /**
     * log filename, like "logPath/yyyy-MM-dd/9999.log"
     *
     * @param triggerDate
     * @param logId
     * @return
     */
    public static String makeLogFileName(Date triggerDate, long logId) {

        // filePath/yyyy-MM-dd
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");    // avoid concurrent problem, can not be static
        File logFilePath = new File(getLogPath(), sdf.format(triggerDate));
        if (!logFilePath.exists()) {
            logFilePath.mkdir();
        }

        // filePath/yyyy-MM-dd/9999.log
        String logFileName = logFilePath.getPath()
                .concat(File.separator)
                .concat(String.valueOf(logId))
                .concat(".log");
        return logFileName;
    }

    /**
     * append log to file
     *
     * @param logFileName log file name
     * @param appendLog   log content
     */
    public static void appendLog(String logFileName, String appendLog) {

        // log file
        if (logFileName == null || logFileName.trim().length() == 0) {
            return;
        }
        File logFile = new File(logFileName);

        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
                return;
            }
        }

        // log
        if (appendLog == null) {
            appendLog = "";
        }
        appendLog += "\r\n";

        // append file content
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(logFile, true);
            fos.write(appendLog.getBytes(StandardCharsets.UTF_8));
            fos.flush();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }

    }
}

package com.invoicegenerator.utils;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class FileUtils {
    private FileUtils() {}

    public static String timestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
    }

    public static String toSafeFilename(String raw) {
        return raw.replaceAll("[\\\\/:*?\"<>|]+", "_").trim();
    }

    public static String absolutePath(String filename) {
        return new File(filename).getAbsolutePath();
    }
}

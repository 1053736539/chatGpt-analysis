package com.cb.system.util;

/**
 * @Description:
 * @Author: ARPHS
 * @Date: 2025-01-17 15:13
 * @Version: 1.0
 **/
public class WordImportContextHolder {
    private static final ThreadLocal<String> IMPORT_ID_HOLDER = new ThreadLocal<>();

    private static final ThreadLocal<String> USER_NAME_HOLDER = new ThreadLocal<>();

    public static void setImportId(String id) {
        IMPORT_ID_HOLDER.set(id);
    }

    public static String getImportId() {
        return IMPORT_ID_HOLDER.get();
    }

    public static void setUserName(String userName) {
        USER_NAME_HOLDER.set(userName);
    }

    public static String getUserName() {
        return USER_NAME_HOLDER.get();
    }

    public static void clear() {
        IMPORT_ID_HOLDER.remove();
        USER_NAME_HOLDER.remove();
    }
}

package com.keuin.ohmyvanillamc;

public class DebugUtil {
    public static void printThreadInfo(String mark) {
        System.out.printf("[%s] Thread %s : id=%d%n", mark, Thread.currentThread().getName(), Thread.currentThread().getId());
    }
}

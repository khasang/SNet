package io.khasang.snet.controller;

/**
 * Created by cresh on 09.10.16.
 */
public class SQLQueryHandler {
    private static SQLQueryHandler ourInstance = new SQLQueryHandler();

    public static SQLQueryHandler getInstance() {
        return ourInstance;
    }

    private SQLQueryHandler() {
    }
}

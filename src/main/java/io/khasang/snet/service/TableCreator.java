package io.khasang.snet.service;

import org.springframework.stereotype.Component;

@Component
public class TableCreator {

    private QueryHandler queryHandler;

    public TableCreator(QueryHandler queryHandler, String dropSql, String createSql) {
        this.queryHandler = queryHandler;
        this.dropAndCreate(dropSql, createSql);
    }

    private void dropAndCreate(String drop, String create) {
        try {
            queryHandler.createAndDropTable(drop);
            queryHandler.createAndDropTable(create);
        } catch (Exception exc) {
            // logged
            exc.printStackTrace();
        }
    }
}

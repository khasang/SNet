package io.khasang.snet.service;

import org.springframework.stereotype.Component;

@Component
public class TableCreator {

    private QueryHandler queryHandler;
    private String dropSql;
    private String createSql;

    public TableCreator(QueryHandler queryHandler) {
        this.queryHandler = queryHandler;
    }

    public void setDropSql(String dropSql) {
        this.dropSql = dropSql;
    }

    public void setCreateSql(String createSql) {
        this.createSql = createSql;
    }

    public void dropAndCreate() {
        try {
            queryHandler.createAndDropTable(this.dropSql);
            queryHandler.createAndDropTable(this.createSql);
        } catch (Exception exc) {
            // logged
            exc.printStackTrace();
        }
    }
}

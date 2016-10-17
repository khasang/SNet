package io.khasang.snet.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Entity
public class BackupLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "path_to_dump_file")
    private String pathToDumpFile;
    @Type(type = "calendar_date")
    private Date dumpDate;
    @Transient
    private boolean dumpExist=false;

    public BackupLog() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPathToDumpFile() {
        return pathToDumpFile;
    }

    public void setPathToDumpFile(String pathToDumpFile) {
        this.pathToDumpFile = pathToDumpFile;
    }

    public boolean isDumpExist() {
        return dumpExist;
    }

    public void setDumpExist(boolean dumpExist) {
        this.dumpExist = dumpExist;
    }

    public Date getDumpDate() {
        return dumpDate;
    }

    public void setDumpDate(Date dumpDate) {
        this.dumpDate = dumpDate;
    }
}

package io.khasang.snet.entity;

public interface AbstractEntity<Identifier> {
    void setID(Identifier id);
    Identifier getID();
}

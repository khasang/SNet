package io.khasang.snet.entity;

/* Abstract entity just for your dao */
public interface AbstractEntity<Identifier> {
    void setID(Identifier id);
    Identifier getID();
}

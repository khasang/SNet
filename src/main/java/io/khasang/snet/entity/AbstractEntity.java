package io.khasang.snet.entity;

/* Abstract entity just for your repository */
public interface AbstractEntity<Identifier> {
    void setID(Identifier id);
    Identifier getID();
}

package io.khasang.snet.dao;

import java.util.List;

public interface DataUtility<Element, Key> {

    /*
    * Adds new object into ORM
    * */
    void add(Element element);

    /* Gets one of the existed objects
     * by key value
     */
    Element get(Key key);

    /* Edit object that already exist
    * */
    void edit(Element element);

    /* Delete's one of the existed objects
     * by key value
     */
    void delete(Key key);

    /* Gets all objects in table
    * */
    List<Element> getAll();
}

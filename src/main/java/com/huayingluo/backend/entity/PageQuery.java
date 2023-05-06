package com.huayingluo.backend.entity;

public class PageQuery<T> {
    private int start;
    private int length;
    private T query;

    public T getQuery() {
        return query;
    }

    public void setQuery(T query) {
        this.query = query;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
    public int getPage(){
       return start/length;
    }
}
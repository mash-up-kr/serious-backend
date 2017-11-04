package com.sheennae.serious.model;


import java.util.List;

public class BaseListModel<T> {

    private List<T> datas;

    private int cursor;

    private int count;

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    public int getCursor() {
        return cursor;
    }

    public void setCursor(int cursor) {
        this.cursor = cursor;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseListModel<?> that = (BaseListModel<?>) o;

        if (cursor != that.cursor) return false;
        if (count != that.count) return false;
        return datas != null ? datas.equals(that.datas) : that.datas == null;
    }

    @Override
    public int hashCode() {
        int result = datas != null ? datas.hashCode() : 0;
        result = 31 * result + cursor;
        result = 31 * result + count;
        return result;
    }

    @Override
    public String toString() {
        return "BaseListModel{" +
                "datas=" + datas +
                ", cursor=" + cursor +
                ", count=" + count +
                '}';
    }
}

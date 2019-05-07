package cn.wdb.domain;

import java.util.List;

public class PageBean<T> {
    private int totalCount;//总数
    private int currentPage;//当前所在页
    private int raw; //一页显示的个数
    private List<T> list; //装载用户信息的集合
    private int totalPage;//总页数

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getRaw() {
        return raw;
    }

    public void setRaw(int raw) {
        this.raw = raw;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "totalCount=" + totalCount +
                ", currentPage=" + currentPage +
                ", raw=" + raw +
                ", list=" + list +
                ", totalPage=" + totalPage +
                '}';
    }
}

package com.pigtom.diary.common;

import com.github.pagehelper.Page;
import lombok.Data;

import java.util.List;

/**
 * @author tangdunhong
 * @blame tangdunhong
 * @module diary
 * @since 2019/10/9 8:52 PM
 **/
@Data
public class PageList<T>  extends Pager {
    /**
     * 实际的页面数据
     */
    private List<T> list;

    public PageList(){}

    public PageList(List<T> list) {
        if (list instanceof Page) {
            Page<T> page = ((Page<T>)list);
            this.list = page.getResult();
            setPageIndex(page.getPageNum());
            setPageSize(getPageSize());
        }
        else {
            this.list = list;
        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PageList{");
        if (list != null) {
            sb.append("list=");
            list.forEach((li) -> sb.append(li + ", "));
        }
        sb.append("}");
        return sb.toString();
    }
}

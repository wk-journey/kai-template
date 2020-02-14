package kai.template.vo.common.base;

import java.util.List;

/**
 * Description    :     分页器
 */
public class Pager<E> {
    private int page;
    private int pageSize;
    private List<E> list;

    public Pager() {
    }

    public Pager(int page, int pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<E> getList() {
        return list;
    }

    public void setList(List<E> list) {
        this.list = list;
    }
}

package cn.magnet.pojo;

import java.util.List;

/**
 * 2020/6/17 21:50
 *
 * @author Evan Ma
 * @since
 **/
public class MagnetLinkResult {

    private List<MagnetLinkField> rows;

    private Integer pageTotal;

    public List<MagnetLinkField> getRows() {
        return rows;
    }

    public void setRows(List<MagnetLinkField> rows) {
        this.rows = rows;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }
}
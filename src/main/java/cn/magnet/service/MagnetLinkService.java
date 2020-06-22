package cn.magnet.service;

import cn.magnet.pojo.MagnetLink;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * javaAppDesign
 *
 * 业务层接口
 * 2020/6/15 12:50
 *
 * @author Evan Ma
 * @since
 **/
public interface MagnetLinkService{

    /**
     * 保存磁力链接信息
     *
     * @param magnetLink
     */
    public void save(MagnetLink magnetLink);


    /**
     * 根据条件查询磁力链接
     *
     * @param magnetLink
     * @return
     */
    public List<MagnetLink> findMagnetLink(MagnetLink magnetLink);

    public Page<MagnetLink> findMagnetLinkByPage(int page, int rows);

    /**
     * 删除磁力链接
     * @param link
     */
    public void delete(MagnetLink link);

    List<MagnetLink> findMagnetLinkById(MagnetLink curLink);

    public int updateMagnet(MagnetLink curLink);

    public List<MagnetLink> findMagnetLinkByLink(MagnetLink magnetLink);


}

package cn.magnet.service;

import cn.magnet.pojo.MagnetLink;
import cn.magnet.pojo.MagnetLinkField;
import cn.magnet.pojo.MagnetLinkResult;
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
public interface MagnetLinkRepositoryService {

    /**
     * 保存磁力链接信息
     *
     * @param magnetLink
     */
    public void save(MagnetLinkField magnetLink);


    /**
     * 批量保存
     *
     * @param magnetLinkFields
     */
    public void saveAll(List<MagnetLinkField> magnetLinkFields);

    public MagnetLinkResult search(String pmagnet_link, String pmagnetlink_key, String pfrom_which_se, Integer page);

    public void delete(MagnetLinkField magnetLink);
}

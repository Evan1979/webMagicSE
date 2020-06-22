package cn.magnet.service.impl;

/**
 * 2020/6/15 12:51
 * 业务层接口实现
 *
 * @author Evan Ma
 * @since
 **/

import cn.magnet.dao.MagnetLinkDao;
import cn.magnet.pojo.MagnetLink;
import cn.magnet.service.MagnetLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

/**
 * 相当于添加bean配置信息
 * @author Evan
 */
@Service
public class MagnetLinkServiceImpl implements MagnetLinkService {
    /**
     *   注入Dao
     */
    @Autowired
    private MagnetLinkDao magnetLinkDao;

    /**
        保存磁力链接信息
     */
    @Override
    @Transactional
    public void save(MagnetLink magnetLink){

        MagnetLink param = new MagnetLink();
        param.setMagnetLink(magnetLink.getMagnetLink());
        param.setCrawledDate(magnetLink.getCrawledDate());

        //执行查询
        List<MagnetLink> list = this.findMagnetLink(param);

        //判断查询结果是否为空
        if (list.size() == 0) {
            //如果查询结果为空，表示磁力链接数据不存在，或者已经更新了，需要新增或者更新数据库
            this.magnetLinkDao.saveAndFlush(magnetLink);
        }
    }

    /**
     根据条件查询磁力链接
     */
    @Override
    public List<MagnetLink> findMagnetLink(MagnetLink magnetLink){
        //设置查询条件
        Example example = Example.of(magnetLink);

        List list = this.magnetLinkDao.findAll(example);
        return list;
    }

    @Override
    public Page<MagnetLink> findMagnetLinkByPage(int page, int rows) {
        Page<MagnetLink> magnetLinks = this.magnetLinkDao.findAll(PageRequest.of(page - 1, rows));
        return magnetLinks;
    }

    /**
     * 删除磁力链接
     * @param link
     */
    @Override
    @Transactional
    public void delete(MagnetLink link) {
        this.magnetLinkDao.delete(link);
    }

    @Override
    public List<MagnetLink> findMagnetLinkById(MagnetLink curLink) {
        List<MagnetLink> magnetLinkById = this.magnetLinkDao.findMagnetLinkById(curLink.getPid());
        return magnetLinkById;
    }

    @Override
    @Transactional
    public int updateMagnet(MagnetLink curLink) {
        int rowsUpdate = this.magnetLinkDao.updateMagnetLink(curLink.getPid(), curLink.getMagnetLinkKey(), curLink.getFromWhichSe(), curLink.getMagnetLink());
        return rowsUpdate;
    }

    @Override
    public List<MagnetLink> findMagnetLinkByLink(MagnetLink magnetLink) {
        List<MagnetLink> magnetLinkByMagnetLink = this.magnetLinkDao.findMagnetLinkByMagnetLink(magnetLink.getMagnetLink());
        return magnetLinkByMagnetLink;
    }


}

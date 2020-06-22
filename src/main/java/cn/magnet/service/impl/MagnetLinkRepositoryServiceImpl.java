package cn.magnet.service.impl;

/**
 * 2020/6/15 12:51
 * 业务层接口实现
 *
 * @author Evan Ma
 * @since
 **/


import cn.magnet.dao.MagnetLinkRepository;
import cn.magnet.pojo.MagnetLink;
import cn.magnet.pojo.MagnetLinkField;
import cn.magnet.pojo.MagnetLinkResult;
import cn.magnet.service.MagnetLinkRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 相当于添加bean配置信息
 */
@Service
public class MagnetLinkRepositoryServiceImpl implements MagnetLinkRepositoryService {

    @Autowired
    MagnetLinkRepository magnetLinkRepository;

    @Override
    public void save(MagnetLinkField magnetLink) {
        this.magnetLinkRepository.save(magnetLink);
    }

    @Override
    public void saveAll(List<MagnetLinkField> magnetLinkFields) {
        this.magnetLinkRepository.saveAll(magnetLinkFields);
    }

    @Override
    public MagnetLinkResult search(String pmagnet_link, String pmagnetlink_key, String pfrom_which_se, Integer page) {
        // System.out.println("pmagnet_link=="+pmagnet_link);
        // System.out.println("pmagnetlink_key=="+pmagnetlink_key);
        // System.out.println("pfrom_which_se=="+pfrom_which_se);
        // System.out.println("page=="+page);

        //判断关键字是否为空     是的话进行默认赋值

        System.out.println(0000);

        //调用dao的方法执行查询
        // Page<MagnetLinkField> pages = this.magnetLinkRepository.findByMagnetLinkAndMagnetLinkKeyAndFromWhichSe(pmagnet_link,
        //         pmagnetlink_key, pfrom_which_se, PageRequest.of(page-1,30));
        //调用dao的方法执行查询
        Page<MagnetLinkField> pages = this.magnetLinkRepository.findByMagnetLinkKey(pmagnetlink_key, PageRequest.of(page-1,300));

        System.out.println(1111);
        // System.out.println("123page before");
        //封装结果对象MagnetLinkResult
        MagnetLinkResult magnetLinkResult = new MagnetLinkResult();

        // System.out.println("123magnetLinkResult before");
        // System.out.println("pages.getContent()===\n"+pages.getContent());
        //设置结果集
        magnetLinkResult.setRows(pages.getContent());
        //设置总页数
        magnetLinkResult.setPageTotal(pages.getTotalPages());

        return magnetLinkResult;
    }


    @Override
    public void delete(MagnetLinkField magnetLink) {
        this.magnetLinkRepository.delete(magnetLink);
    }

}

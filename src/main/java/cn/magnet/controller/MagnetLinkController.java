package cn.magnet.controller;

import cn.magnet.pojo.MagnetLink;
import cn.magnet.pojo.MagnetLinkField;
import cn.magnet.pojo.User;
import cn.magnet.service.MagnetLinkRepositoryService;
import cn.magnet.service.MagnetLinkService;
import cn.magnet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 2020/6/21 15:28
 *
 * @author Evan Ma
 * @since
 **/
@RestController
public class MagnetLinkController {

    @Autowired
    MagnetLinkService magnetLinkService;


    @Autowired
    private MagnetLinkRepositoryService magnetLinkRepositoryService;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;


    @RequestMapping(value = "delMagnetLinkInfo",method = RequestMethod.POST)
    public String getDeleteResult(String state,Long id){
        String result = "true";
        MagnetLink link = new MagnetLink();
        link.setPid(id);

        this.magnetLinkService.delete(link);
        List<MagnetLink> magnetLink = this.magnetLinkService.findMagnetLink(link);
        if (magnetLink.size() != 0) {
            result = "false";
        }

        MagnetLinkField linkField = new MagnetLinkField();
        linkField.setPid(id);

        //更新es索引库
        this.magnetLinkRepositoryService.delete(linkField);
        return result;
    }


    @RequestMapping(value = "changeMagnetLinkInfo",method = RequestMethod.POST)
    @Transactional
    public String getChangeResult(Long id, String magnetLinkKey, String fromWhichSe, String magnetLink){
        String result = "false";
        int rowsUpdate = 0;
        MagnetLink curLink = new MagnetLink();
        curLink.setPid(id);
        curLink.setMagnetLinkKey(magnetLinkKey);
        curLink.setFromWhichSe(fromWhichSe);
        curLink.setMagnetLink(magnetLink);

        // List<MagnetLink> user = this.magnetLinkService.findMagnetLink(curLink);
        List<MagnetLink> magnetLinks = this.magnetLinkService.findMagnetLinkById(curLink);
        if (magnetLinks.size() != 0) {
            MagnetLink magnetLink1 = magnetLinks.get(0);
            //已经存在  进行更新
            //原有信息不变
            curLink.setMagnetLinkFileSize(magnetLink1.getMagnetLinkFileSize());
            curLink.setCrawledDate(magnetLink1.getCrawledDate());
            curLink.setMagnetDownloadTimes(magnetLink1.getMagnetDownloadTimes());
            rowsUpdate = this.magnetLinkService.updateMagnet(curLink);

        }else {   //size=0
            //不存在  进行新增操作
            this.magnetLinkService.save(curLink);
        }

        if (rowsUpdate == 1){
            //更新索引库
            List<MagnetLink> magnetLinkServiceMagnetLinkById = this.magnetLinkService.findMagnetLinkById(curLink);
            MagnetLink updatedLinks = magnetLinkServiceMagnetLinkById.get(0);
            MagnetLinkField linkField = new MagnetLinkField();
            linkField.setPid(id);
            linkField.setCrawledDate(updatedLinks.getCrawledDate());
            linkField.setFromWhichSe(fromWhichSe);
            linkField.setMagnetDownloadTimes(updatedLinks.getMagnetDownloadTimes());
            linkField.setMagnetLinkKey(magnetLinkKey);
            linkField.setMagnetLinkFileSize(updatedLinks.getMagnetLinkFileSize());
            linkField.setMagnetLink(magnetLink);
            linkField.setMagnetLinkSize(updatedLinks.getMagnetLinkSize());
            this.magnetLinkRepositoryService.save(linkField);
            result = "true";
        }
        return result;

    }
}

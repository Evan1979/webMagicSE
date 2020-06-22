package cn.magnet.controller;

import cn.magnet.pojo.MagnetLinkResult;
import cn.magnet.service.MagnetLinkRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

    @Autowired
    private MagnetLinkRepositoryService magnetLinkRepositoryService;
    /**
     * 根据条件分页查询磁力链接
     * @param pmagnet_link
     * @param pmagnetlink_key
     * @param pfrom_which_se
     * @param page
     * @return
     */
    @RequestMapping(value = "search",method = RequestMethod.POST)
    public MagnetLinkResult search(String pmagnet_link, String pmagnetlink_key, String pfrom_which_se,Integer page) {
        MagnetLinkResult magnetLinkResult =  this.magnetLinkRepositoryService.search(pmagnet_link,pmagnetlink_key,pfrom_which_se,page);
        return magnetLinkResult;
    }
}

package cn.magnet.test;

import cn.magnet.Application;
import cn.magnet.pojo.MagnetLink;
import cn.magnet.pojo.MagnetLinkField;
import cn.magnet.service.MagnetLinkRepositoryService;
import cn.magnet.service.MagnetLinkService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class ESTest {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private MagnetLinkService magnetLinkService;
    @Autowired
    private MagnetLinkRepositoryService magnetLinkRepositoryService;

    //创建索引和映射
    @Test
    public void createIndex() {
        this.elasticsearchTemplate.createIndex(MagnetLinkField.class);
        this.elasticsearchTemplate.putMapping(MagnetLinkField.class);
    }

    @Test
    public void delete(){
        this.elasticsearchTemplate.deleteIndex(MagnetLinkField.class);
    }

    // @Test
    // public void del(){
    //     MagnetLinkField linkField = new MagnetLinkField();
    //     linkField.setPid(Long.parseLong("144"));
    //
    //     //更新es索引库
    //     this.magnetLinkRepositoryService.delete(linkField);
    //
    //     MagnetLinkField linkField1 = new MagnetLinkField();
    //     linkField.setPid(Long.parseLong("143"));
    //
    //     //更新es索引库
    //     this.magnetLinkRepositoryService.delete(linkField1);
    // }


    @Test
    public void magnetLinkData() {
        //声明页码数，从1开始
        int p = 1;
        //声明查询到的数据条数
        int pageSize = 0;

        do {
            //从数据库中查询数据
            Page<MagnetLink> page = this.magnetLinkService.findMagnetLinkByPage(p, 500);

            //声明容器存放JobInfoField
            List<MagnetLinkField> list = new ArrayList<>();

            //把查询到的数据封装为MagnetLinkField
            for (MagnetLink magnetLink : page.getContent()) {
                //声明对象
                MagnetLinkField magnetLinkField = new MagnetLinkField();
                // //封装数据,复制数据
                BeanUtils.copyProperties(magnetLink, magnetLinkField);

                //把封装好数据的对象放到list容器中
                list.add(magnetLinkField);

            }

            //把封装好的数据保存到索引库中
            this.magnetLinkRepositoryService.saveAll(list);

            //页码数加一
            p++;

            //获取查询结果集的数据条数
            pageSize = page.getContent().size();

        } while (pageSize == 500);

    }
}

package cn.magnet.dao;

import cn.magnet.pojo.MagnetLinkField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * javaAppDesign
 * 2020/6/15 12:53
 * 持久层接口
 *
 * @author Evan Ma
 * @since
 **/
@Component
public interface MagnetLinkRepository extends ElasticsearchRepository<MagnetLinkField, Long> {

    Page<MagnetLinkField> findByMagnetLinkKey(String pmagnetlink_key,Pageable pageable);

}

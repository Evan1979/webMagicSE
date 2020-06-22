package cn.magnet.dao;

import cn.magnet.pojo.MagnetLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * javaAppDesign
 * 2020/6/15 12:53
 * 持久层接口
 *
 * @author Evan Ma
 * @since
 **/
@Component
public interface MagnetLinkDao extends JpaRepository<MagnetLink, Long> {  //long主键类型
    @Query(value="select * from pages where pid = ?" ,nativeQuery=true)
    public List<MagnetLink> findMagnetLinkById(Long id);


    @Modifying
    @Query(value="update MagnetLink set magnetLinkKey=:magnetLinkKey,fromWhichSe=:fromWhichSe,magnetLink=:magnetLink where pid =:id")
    public int updateMagnetLink(@Param("id") Long id, @Param("magnetLinkKey") String magnetLinkKey,
                          @Param("fromWhichSe") String fromWhichSe, @Param("magnetLink") String magnetLink);


    @Query(value="select * from pages where pmagnet_link = ?" ,nativeQuery=true)
    public List<MagnetLink> findMagnetLinkByMagnetLink(String link);

}

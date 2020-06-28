package cn.magnet.dao;

import cn.magnet.pojo.MagnetLink;
import cn.magnet.pojo.User;
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
public interface UserDao extends JpaRepository<User, Long> {  //long主键类型
    // (Long id, String name, String email, String phone)

    // UPDATE `aurora_search`.`user` SET `upassword` = '2', `ubirthday` = '3' WHERE (`uid` = '4');

    @Query(value="select * from user where uId = ?" ,nativeQuery=true)
    public List<User> findByUId(Long id);

    @Modifying
    @Query(value="update User set uName=:newName,uEmail=:newEmail,uPhone=:newPhone where uId =:id")
    public int updateUser(@Param("id") Long id, @Param("newName") String newName,
                          @Param("newEmail") String newEmail, @Param("newPhone") String newPhone);

    @Query(value="select * from user where uName = ? and uPassword=?" ,nativeQuery=true)
    public User findByUNameAndUPassword(String name,String password);
}

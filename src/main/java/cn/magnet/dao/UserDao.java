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

    /**
     * 自定义的根据用户的id来查询用户信息( @Query注解用来定义请求的sql语句)
     * @param id
     * @return
     */
    @Query(value="select * from user where uId = ?" ,nativeQuery=true)
    public List<User> findByUId(Long id);

    /**
     * 自定义的用户更新操作 (更新类的语句需要添加 @Modify注解)
     *
     * 1：方法的返回值应该是int，表示更新语句所影响的行数
     * 2：在调用的地方必须加事务，没有事务不能正常执行
     *
     * @param id
     * @param newName
     * @param newPassword
     * @param newEmail
     * @param newPhone
     * @return
     */
    @Modifying
    @Query(value="update User set uName=:newName,uEmail=:newEmail,uPassword=:newPassword,uPhone=:newPhone where uId =:id")
    public int updateUser(@Param("id") Long id, @Param("newName") String newName,@Param("newPassword") String newPassword,
                          @Param("newEmail") String newEmail, @Param("newPhone") String newPhone);


    /**
     * 自定义的根据用户的用户名和用户密码来查询用户的信息
     * @param name
     * @param password
     * @return
     */
    @Query(value="select * from user where uName = ? and uPassword=?" ,nativeQuery=true)
    public User findByUNameAndUPassword(String name,String password);

    /**
     * 根据用户email查询用户
     * @param uEmail
     * @return
     */
    @Query(value="select * from user where uEmail = ?" ,nativeQuery=true)
    User findByUEmail(String uEmail);
}

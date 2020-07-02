package cn.magnet.service;

import cn.magnet.pojo.User;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * itcast-crawler-job
 * 2020/6/19 15:17
 *
 * @author Evan Ma
 * @since
 **/
public interface UserService {

    /**
     * 保存用户
     *
     * @param user
     * @return
     */
    public int save(User user);


    /**
     * 根据条件查询用户
     *
     * @param user
     * @return
     */
    public List<User> findUser(User user);


    /**
     * 根据用户名和用户密码查找用户
     * @param user
     * @return
     */
    public User findUserByNameAndPassword(User user);


    /**
     * 获取所有用户信息
     * @param page
     * @param rows
     * @return
     */
    public Page<User> findUserByPage(int page, int rows);


    /**
     * 删除用户
     * @param u
     */
    public void delete(User u);

    /**
     * 根据用户email查询用户
     * @param u
     * @return
     */
    User findUserByEmail(User u);

    int addUser(User u);
}

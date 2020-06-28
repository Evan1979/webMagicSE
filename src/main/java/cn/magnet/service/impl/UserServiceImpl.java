package cn.magnet.service.impl;

import cn.magnet.dao.UserDao;
import cn.magnet.pojo.*;
import cn.magnet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 2020/6/19 15:19
 * 业务层用户操作
 * @author Evan Ma
 * @since
 **/

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserDao userDao;

    @Override
    @Transactional
    public int save(User user) {
        int rowsChange = 0;
        User changeUser = new User();
        changeUser.setuId(user.getuId());

        //执行查询
        List<User> list = this.userDao.findByUId(user.getuId());
        User primaryUser = list.get(0);
        System.out.println("afterUser=="+primaryUser);

        //判断查询结果是否为空
        if (list.size() == 0) {
            changeUser.setuBirthday(new Date());
            changeUser.setuCreateDate(new Date());
            changeUser.setuPassword("123");
            //如果查询结果为空，表示用户不存在，或者已经更新了，需要新增或者更新数据库
            this.userDao.saveAndFlush(primaryUser);
        }else {
            //已存在   进行更新
            // primaryUser.setuName(user.getuName());
            // primaryUser.setuPhone(user.getuPhone());
            // primaryUser.setuEmail(user.getuEmail());
            rowsChange = this.userDao
                    .updateUser(
                            user.getuId(), user.getuName(), user.getuEmail(), user.getuPhone()
                    );
            System.out.println("rowsChange=="+ rowsChange);

        }
        return rowsChange;
    }

    @Override
    public List<User> findUser(User user) {
        //声明查询条件
        Example<User> example = Example.of(user);

        List<User> users = this.userDao.findAll(example);
        return users;
    }

    @Override
    public User findUserByNameAndPassword(User user) {
        //执行查询
        User checkedUser = this.userDao.findByUNameAndUPassword(user.getuName(), user.getuPassword());
        // System.out.println("1111");
        //
        // if (checkedUser != null){
        //     System.out.println("byUNameAndUPassword=="+ checkedUser);
        //
        // }

        return checkedUser;
    }

    @Override
    public Page<User> findUserByPage(int page, int rows) {
        Page<User> users = this.userDao.findAll(PageRequest.of(page - 1, rows));
        return users;
    }



    @Override
    public void delete(User u) {
        this.userDao.delete(u);
    }


}

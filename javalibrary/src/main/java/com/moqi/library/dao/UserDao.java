package com.moqi.library.dao;

import com.moqi.core.exception.BusinessException;
import com.moqi.core.model.ReturnNo;
import com.moqi.library.dao.bo.User;
import com.moqi.library.mapper.UserPoMapper;
import com.moqi.library.mapper.mapper.UserMapper;
import com.moqi.library.mapper.po.UserPo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    private final static Logger logger = LoggerFactory.getLogger(UserDao.class);
    private UserPoMapper userPoMapper;
    private UserMapper userMapper;

    @Autowired
    public UserDao(UserPoMapper userPoMapper, UserMapper userMapper) {
        this.userPoMapper = userPoMapper;
        this.userMapper = userMapper;
    }

    /**
     * 创建一个新用户
     *
     * @param user 用户信息（BO层）
     * @return 新创建的用户（BO层）
     * @throws BusinessException 自定义业务异常
     */
    public User createUser(User user) throws BusinessException {
        UserPo userPo = userMapper.userToUserPo(user);
        userPoMapper.insertSelective(userPo);
        return user;
    }


    /**
     * 根据id查找用户
     *
     * @param userId 用户id
     * @return user对象
     * @throws BusinessException 抛出异常
     */
    public User getUserById(Long userId) throws BusinessException {
        UserPo po = userPoMapper.selectByPrimaryKey(userId);
        if (po == null) {
            throw new BusinessException(ReturnNo.USER_INVALID_ACCOUNT, "用户不存在");
        }
        return userMapper.userPoToUser(po);
    }

    /**
     * 根据 OpenID 查找用户
     *
     * @param openid 微信 OpenID
     * @return 用户信息（BO层）
     * @throws BusinessException 自定义业务异常
     */
    public User getUserByOpenid(String openid) throws BusinessException {
        UserPo po = userPoMapper.selectByOpenid(openid);
        if (po == null) {
            throw new BusinessException(ReturnNo.USER_INVALID_ACCOUNT, "用户不存在");
        }
        return userMapper.userPoToUser(po);
    }

    /**
     * 更改用户权限
     *
     * @param userId 用户id
     * @param role 用户权限
     */
    public void updateUserRole(Long userId, String role) {
        UserPo po = userPoMapper.selectByPrimaryKey(userId);
        po.setRole(role);
        userPoMapper.updateByPrimaryKeySelective(po);
    }
}

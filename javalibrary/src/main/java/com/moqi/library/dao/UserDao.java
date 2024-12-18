package com.moqi.library.dao;

import com.moqi.core.exception.BusinessException;
import com.moqi.core.model.ReturnNo;
import com.moqi.library.dao.bo.User;
import com.moqi.library.mapper.UserPoMapper;
import com.moqi.library.mapper.mapper.UserMapper;
import com.moqi.library.mapper.po.UserPo;
import com.moqi.library.mapper.po.UserPoExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        // 创建 UserExample，用于构建查询条件
        UserPoExample example = new UserPoExample();
        UserPoExample.Criteria criteria = example.createCriteria();
        criteria.andOpenidEqualTo(openid); // 设置查询条件：openid等于传入的openID

        // 使用 MyBatis 生成的 selectByExample 方法查询用户信息
        List<UserPo> userPos = userPoMapper.selectByExample(example);

        // 检查查询结果是否为空
        if (userPos == null || userPos.isEmpty()) {
            throw new BusinessException(ReturnNo.USER_INVALID_ACCOUNT, "用户不存在");
        }

        UserPo po = userPos.get(0);

        // 将 UserPo 转换为 User 对象并返回
        return userMapper.userPoToUser(po);
    }

    /**
     * 更改用户权限
     *
     * @param userId 用户id
     * @param role 用户权限
     */
    public void updateUserRole(Long userId, String role) throws BusinessException {
        UserPo po = userPoMapper.selectByPrimaryKey(userId);
        po.setRole(role);
        userPoMapper.updateByPrimaryKeySelective(po);
    }

    public User updateUserInfo(User user) throws BusinessException {
        UserPo po = userMapper.userToUserPo(user);
        int ret = userPoMapper.updateByPrimaryKeySelective(po);
        if (ret == 0) {
            throw new BusinessException(ReturnNo.BOOK_NOT_FOUND);
        }
        return userMapper.userPoToUser(po);
    }
}

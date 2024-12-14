package com.moqi.library.mapper.mapper;

import com.moqi.library.controller.dto.UserDto;
import com.moqi.library.controller.vo.UserVo;
import com.moqi.library.dao.bo.User;
import com.moqi.library.mapper.po.UserPo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserPo userToUserPo(User user);
    User userPoToUser(UserPo userPo);
    UserDto userToUserDto(User user);
    User userVoToUser(UserVo userVo);
}

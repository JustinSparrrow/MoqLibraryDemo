package com.moqi.library.mapper.mapper;

import com.moqi.library.controller.dto.UserDto;
import com.moqi.library.controller.vo.UserVo;
import com.moqi.library.dao.bo.User;
import com.moqi.library.mapper.po.UserPo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    @Mapping(target = "createdAt", source = "createdAt", qualifiedByName = "mapTimestampToLocalDateTime")
    @Mapping(target = "updatedAt", source = "updatedAt", qualifiedByName = "mapTimestampToLocalDateTime")
    UserPo userToUserPo(User user);
    @Mapping(target = "createdAt", source = "createdAt", qualifiedByName = "mapLocalDateTimeToTimestamp")
    @Mapping(target = "updatedAt", source = "updatedAt", qualifiedByName = "mapLocalDateTimeToTimestamp")
    User userPoToUser(UserPo userPo);
    UserDto userToUserDto(User user);
    User userVoToUser(UserVo userVo);

    @Named("mapLocalDateTimeToTimestamp")
    static Timestamp mapLocalDateTimeToTimestamp(LocalDateTime localDateTime) {
        return localDateTime == null ? null : Timestamp.valueOf(localDateTime);
    }

    @Named("mapTimestampToLocalDateTime")
    static LocalDateTime mapTimestampToLocalDateTime(Timestamp timestamp) {
        return timestamp == null ? null : timestamp.toLocalDateTime();
    }
}

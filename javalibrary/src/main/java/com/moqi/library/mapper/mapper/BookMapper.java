package com.moqi.library.mapper.mapper;

import com.moqi.library.controller.dto.BookDto;
import com.moqi.library.controller.vo.BookVo;
import com.moqi.library.dao.bo.Book;
import com.moqi.library.mapper.po.BookPo;
import com.moqi.library.mapper.po.BookPoWithBLOBs;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    // 将 Book 转换为 BookPo
    @Mapping(target = "createdAt", source = "createdAt", qualifiedByName = "mapTimestampToLocalDateTime")
    @Mapping(target = "updatedAt", source = "updatedAt", qualifiedByName = "mapTimestampToLocalDateTime")
    BookPo bookToBookPo(Book book);

    // 将 Book 转换为 BookPoWithBLOBs
    @Mapping(target = "createdAt", source = "createdAt", qualifiedByName = "mapTimestampToLocalDateTime")
    @Mapping(target = "updatedAt", source = "updatedAt", qualifiedByName = "mapTimestampToLocalDateTime")
    BookPoWithBLOBs bookToBookPoWithBLOBs(Book book);

    // 将 BookPo 转换为 Book
    @Mapping(target = "createdAt", source = "createdAt", qualifiedByName = "mapLocalDateTimeToTimestamp")
    @Mapping(target = "updatedAt", source = "updatedAt", qualifiedByName = "mapLocalDateTimeToTimestamp")
    Book bookPoToBook(BookPo bookPo);

    // 将 BookPoWithBLOBs 转换为 Book
    @Mapping(target = "createdAt", source = "createdAt", qualifiedByName = "mapLocalDateTimeToTimestamp")
    @Mapping(target = "updatedAt", source = "updatedAt", qualifiedByName = "mapLocalDateTimeToTimestamp")
    Book bookPoWithBLOBsToBook(BookPoWithBLOBs bookPoWithBLOBs);

    BookDto bookTOBookDto(Book book);

    Book bookVoToBook(BookVo bookVo);

    @Named("mapLocalDateTimeToTimestamp")
    static Timestamp mapLocalDateTimeToTimestamp(LocalDateTime localDateTime) {
        return localDateTime == null ? null : Timestamp.valueOf(localDateTime);
    }

    @Named("mapTimestampToLocalDateTime")
    static LocalDateTime mapTimestampToLocalDateTime(Timestamp timestamp) {
        return timestamp == null ? null : timestamp.toLocalDateTime();
    }
}

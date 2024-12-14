package com.moqi.library.mapper.mapper;

import com.moqi.library.controller.dto.DonationDto;
import com.moqi.library.controller.vo.DonationVo;
import com.moqi.library.dao.bo.Donation;
import com.moqi.library.mapper.po.DonationPo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Mapper
public interface DonationMapper {
    DonationMapper INSTANCE = Mappers.getMapper(DonationMapper.class);

    @Mapping(target = "createdAt", source = "createdAt", qualifiedByName = "mapTimestampToLocalDateTime")
    DonationPo donationToDonationPo(Donation donation);

    @Mapping(target = "createdAt", source = "createdAt", qualifiedByName = "mapLocalDateTimeToTimestamp")
    Donation donationPoToDonation(DonationPo donationPo);

    DonationDto donationToDonationDto(Donation donation);

    Donation donationVoToDonation(DonationVo donationVo);

    @Named("mapLocalDateTimeToTimestamp")
    static Timestamp mapLocalDateTimeToTimestamp(LocalDateTime localDateTime) {
        return localDateTime == null ? null : Timestamp.valueOf(localDateTime);
    }

    @Named("mapTimestampToLocalDateTime")
    static LocalDateTime mapTimestampToLocalDateTime(Timestamp timestamp) {
        return timestamp == null ? null : timestamp.toLocalDateTime();
    }
}

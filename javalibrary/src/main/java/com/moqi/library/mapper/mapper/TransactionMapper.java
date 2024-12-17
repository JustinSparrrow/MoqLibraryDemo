package com.moqi.library.mapper.mapper;

import com.moqi.library.controller.dto.DonationDto;
import com.moqi.library.controller.dto.TransactionDto;
import com.moqi.library.controller.vo.DonationVo;
import com.moqi.library.controller.vo.TransactionVo;
import com.moqi.library.dao.bo.Donation;
import com.moqi.library.dao.bo.Transaction;
import com.moqi.library.mapper.po.DonationPo;
import com.moqi.library.mapper.po.TransactionPo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Mapper
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    @Mapping(target = "createdAt", source = "createdAt", qualifiedByName = "mapTimestampToLocalDateTime")
    @Mapping(target = "updatedAt", source = "updatedAt", qualifiedByName = "mapTimestampToLocalDateTime")
    TransactionPo transactionToTransactionPo(Transaction transaction);

    @Mapping(target = "createdAt", source = "createdAt", qualifiedByName = "mapLocalDateTimeToTimestamp")
    @Mapping(target = "updatedAt", source = "updatedAt", qualifiedByName = "mapLocalDateTimeToTimestamp")
    Transaction transactionPoTotransaction(TransactionPo transactionPo);

    TransactionDto transactionToTransactionDto(Transaction transaction);

    Transaction transactionVoToTransaction(TransactionVo transactionVo);

    @Named("mapLocalDateTimeToTimestamp")
    static Timestamp mapLocalDateTimeToTimestamp(LocalDateTime localDateTime) {
        return localDateTime == null ? null : Timestamp.valueOf(localDateTime);
    }

    @Named("mapTimestampToLocalDateTime")
    static LocalDateTime mapTimestampToLocalDateTime(Timestamp timestamp) {
        return timestamp == null ? null : timestamp.toLocalDateTime();
    }
}
package com.moqi.library.controller.vo;

import lombok.*;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class DonationVo {
    private String bookDesc;
    private Timestamp createdAt;
    private Integer status;
}

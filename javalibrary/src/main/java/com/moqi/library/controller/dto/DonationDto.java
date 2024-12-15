package com.moqi.library.controller.dto;

import lombok.*;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class DonationDto {
    private Long id;               // 捐赠记录唯一标识
    private Long adminId;          // 管理员ID（外键）
    private Long bookId;           // 图书ID（外键）
    private String bookDesc;       // 捐赠者对书籍的介绍
    private Timestamp createdAt;   // 创建时间
    private Integer status;        // 捐赠图书状态
    private String isbn;           // isbn码
}

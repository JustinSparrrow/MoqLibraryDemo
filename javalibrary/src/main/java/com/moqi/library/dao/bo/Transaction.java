package com.moqi.library.dao.bo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("Transaction")
public class Transaction {
    private Long id;               // 借阅记录唯一标识
    private Long userId;           // 用户ID（外键）
    private Long bookId;           // 图书ID（外键）
    private Date borrowDate;       // 借阅日期
    private Date returnDate;       // 归还日期
    private String status;         // 借阅状态：borrowing 或 returned
    private Timestamp createdAt;   // 创建时间
    private Timestamp updatedAt;   // 最后更新时间
}

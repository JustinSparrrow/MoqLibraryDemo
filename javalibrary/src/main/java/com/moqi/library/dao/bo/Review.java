package com.moqi.library.dao.bo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("Review")
public class Review {
    private Long id;               // 评价唯一标识
    private Long userId;           // 用户ID（外键）
    private Long bookId;           // 图书ID（外键）
    private String content;        // 评价内容
    private int rating;            // 评分（1-5分）
    private Timestamp createdAt;   // 创建时间

}

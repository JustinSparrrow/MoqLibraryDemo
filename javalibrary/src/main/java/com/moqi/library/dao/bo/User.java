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
@TableName("User")
public class User {
    private Long id;                // 用户唯一标识
    private String openid;          // 微信登录的唯一标识
    private String phone;           // 手机号
    private String role;            // 用户角色：普通用户(user)或管理员(admin)
    private String nickname;
    private String avatar;
    private Timestamp createdAt;    // 创建时间
    private Timestamp updatedAt;    // 最后更新时间
}

CREATE TABLE User (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,      -- 用户唯一标识
                      openid VARCHAR(64) NOT NULL UNIQUE,        -- 微信登录的唯一标识
                      phone VARCHAR(20) UNIQUE,                  -- 手机号
                      role ENUM('user', 'admin') DEFAULT 'user', -- 用户角色：普通用户(user)或管理员(admin)
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 创建时间
                      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP  -- 最后更新时间
);

CREATE TABLE Book (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,      -- 图书唯一标识
                      isbn VARCHAR(20) NOT NULL UNIQUE,          -- ISBN号
                      book_name VARCHAR(255) NOT NULL,           -- 书名
                      author VARCHAR(255),                       -- 作者
                      press VARCHAR(255),                        -- 出版社
                      press_date DATE,                           -- 出版日期
                      press_place VARCHAR(255),                  -- 出版地点
                      price DECIMAL(10, 2),                      -- 价格
                      pictures TEXT,                             -- 图片链接（JSON格式）
                      clc_code VARCHAR(20),                      -- 中图分类号
                      clc_name VARCHAR(255),                     -- 分类名称
                      book_desc TEXT,                            -- 书籍描述
                      binding ENUM('paperback', 'hardcover'),    -- 装帧方式
                      language VARCHAR(20),                      -- 语言
                      format VARCHAR(50),                        -- 开本尺寸
                      status ENUM('available', 'borrowed') DEFAULT 'available', -- 图书状态
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,          -- 创建时间
                      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP -- 最后更新时间
);

CREATE TABLE Transaction (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,      -- 借阅记录唯一标识
                             user_id BIGINT NOT NULL,                   -- 用户ID（外键）
                             book_id BIGINT NOT NULL,                   -- 图书ID（外键）
                             borrow_date DATE NOT NULL,                 -- 借阅日期
                             return_date DATE,                          -- 归还日期
                             status ENUM('borrowing', 'returned') DEFAULT 'borrowing', -- 借阅状态
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,          -- 创建时间
                             updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 最后更新时间
                             FOREIGN KEY (user_id) REFERENCES User(id), -- 外键约束
                             FOREIGN KEY (book_id) REFERENCES Book(id)  -- 外键约束
);

CREATE TABLE Review (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,      -- 评价唯一标识
                        user_id BIGINT NOT NULL,                   -- 用户ID（外键）
                        book_id BIGINT NOT NULL,                   -- 图书ID（外键）
                        content TEXT,                              -- 评价内容
                        rating INT CHECK (rating BETWEEN 1 AND 5), -- 评分（1-5分）
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 创建时间
                        FOREIGN KEY (user_id) REFERENCES User(id), -- 外键约束
                        FOREIGN KEY (book_id) REFERENCES Book(id)  -- 外键约束
);

CREATE TABLE Donation (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,      -- 捐赠记录唯一标识
                          admin_id BIGINT NOT NULL,                  -- 管理员ID（外键）
                          book_id BIGINT NOT NULL,                   -- 图书ID（外键）
                          book_desc TEXT,                            -- 捐赠者对书籍的介绍
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 创建时间
                          FOREIGN KEY (admin_id) REFERENCES User(id), -- 外键约束
                          FOREIGN KEY (book_id) REFERENCES Book(id)  -- 外键约束
);
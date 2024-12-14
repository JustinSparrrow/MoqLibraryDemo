package com.moqi.library.controller;

import com.moqi.core.exception.BusinessException;
import com.moqi.core.model.ReturnNo;
import com.moqi.library.dao.bo.Book;
import com.moqi.library.service.BookService;
import com.moqi.core.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/books", produces = "application/json;charset=UTF-8")
public class AdminBookController {
    private final Logger logger = LoggerFactory.getLogger(AdminBookController.class);

    private BookService bookService;

    @Autowired
    public AdminBookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * 审核图书
     *
     * @param donationId 捐赠记录ID
     * @param approve    是否通过
     * @param userDetails 用户权限
     * @return Book
     */
    @PostMapping("/approve/{donationId}")
    public Book approveBook(@PathVariable Long donationId,
                            @RequestParam boolean approve,
                            @AuthenticationPrincipal UserDetails userDetails) {
        // 校验用户角色是否为管理员
        boolean isAdmin = userDetails.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("admin"));
        if (!isAdmin) {
            throw new BusinessException(ReturnNo.USER_PERMISSION_DENIED, "无权限操作");
        }
        if (approve) {
            // 审核通过并添加图书
            return bookService.approveAndAddBook(donationId);
        } else {
            // 拒绝捐赠
            bookService.rejectDonation(donationId);
            return null;
        }
    }
}

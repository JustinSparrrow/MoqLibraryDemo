package com.moqi.library.controller;

import com.moqi.library.dao.bo.Donation;
import com.moqi.library.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/donation")
public class DonationController {

    private final DonationService donationService;

    @Autowired
    public DonationController(DonationService donationService) {
        this.donationService = donationService;
    }

    // 捐书
    @PostMapping("/donate")
    public Donation donateBook(@RequestBody Donation donation) {
        return donationService.createDonation(donation);
    }

    // 取消捐书
    @DeleteMapping("/cancel/{id}")
    public void cancelDonation(@PathVariable Long id) {
        donationService.cancelDonation(id);
    }

    // 用户查询自己的捐书记录
    @GetMapping("/user/{userId}")
    public List<Donation> getUserDonations(@PathVariable Long userId,
                                                           @RequestParam int pageNum,
                                                           @RequestParam int pageSize) {
        return donationService.getUserDonations(userId, pageNum, pageSize);
    }

    // 管理员查询某个用户的所有捐书记录
    @GetMapping("/admin/user/{userId}")
    public List<Donation> getDonationsByUser(@PathVariable Long userId,
                                                             @RequestParam int pageNum,
                                                             @RequestParam int pageSize) {
        return donationService.getDonationsByUser(userId, pageNum, pageSize);
    }

    // 管理员查询某本书的捐赠人
    @GetMapping("/admin/book/{bookId}")
    public List<Donation> getDonationsByBook(@PathVariable Long bookId,
                                                             @RequestParam int pageNum,
                                                             @RequestParam int pageSize) {
        return donationService.getDonationsByBook(bookId, pageNum, pageSize);
    }
}

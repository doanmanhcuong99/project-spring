package com.example.demo.service;

import com.example.demo.entity.Account;
import org.springframework.data.domain.Page;

public interface AccountService {
    Page<Account> getList(int page, int limit);

    Account getDetail(String email);

    // Thực hiện xác thực người dùng.
    Account login(String email, String password);

    // Đăng ký tài khoản, mã hoá mật khẩu...
    Account register(Account account);

    // Update thông tin tài khoản theo email.
    Account update(String email, Account account);

    Account getByEmail(String email);
}

package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;
@Service
public class AccountServiceImplement implements AccountService {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Page<Account> getList(int page, int limit) {
        return accountRepository.findAll(PageRequest.of(page - 1, limit));
    }

    @Override
    public Account getDetail(String email) {
        return accountRepository.findById(email).orElse(null);
    }

    @Override
    public Account login(String email, String password) {
        // Tìm tài khoản có email trùng xem tồn tại không.
        Optional<Account> optionalAccount = accountRepository.findById(email);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            if (account.getPassword().equals(password)) {
                return account;
            }
        }

        return null;
    }

    @Override
    public Account register(Account account) {
        // thực hiện mã hoá password
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setCreatedAtMLS(Calendar.getInstance().getTimeInMillis());
        account.setUpdatedAtMLS(Calendar.getInstance().getTimeInMillis());
        account.setRole(Account.Role.USER.getValue());
        account.setStatus(1);
        return accountRepository.save(account);
    }

    @Override
    public Account update(String email, Account account) {
        // Tìm tài khoản có email trùng xem tồn tại không.
        Optional<Account> optionalAccount = accountRepository.findById(email);
        if (optionalAccount.isPresent()) {
            Account existAccount = optionalAccount.get();
            existAccount.setFullName(account.getFullName());
            existAccount.setPhone(account.getPhone());
            existAccount.setAddress(account.getAddress());
            existAccount.setUpdatedAtMLS(Calendar.getInstance().getTimeInMillis());

            return accountRepository.save(existAccount);

        }
        return null;
    }

    @Override
    public Account getByEmail(String email) {
        return accountRepository.findById(email).orElse(null);
    }
}

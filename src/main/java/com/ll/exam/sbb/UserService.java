package com.ll.exam.sbb;

import com.ll.exam.sbb.user.SiteUser;
import com.ll.exam.sbb.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void create(String userName, String email, String password) {
        SiteUser siteUser = new SiteUser();
        siteUser.setUsername(userName);
        siteUser.setEmail(email);
        siteUser.setPassword(passwordEncoder.encode(password));
        userRepository.save(siteUser);
    }
}

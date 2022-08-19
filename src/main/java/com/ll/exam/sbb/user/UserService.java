package com.ll.exam.sbb.user;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void create(String username, String email, String password)
            throws SignupEmailDuplicatedException, SignupUsernameDuplicatedException {
        SiteUser siteUser = new SiteUser();
        siteUser.setUsername(username);
        siteUser.setEmail(email);
        siteUser.setPassword(passwordEncoder.encode(password));

        // 이메일이 중복인지 아이디가 중복인지 처리
        try{
            userRepository.save(siteUser);
        } catch (DataIntegrityViolationException e){
            if (userRepository.existsByUsername(username)){
                throw new SignupUsernameDuplicatedException("이미 사용중인 닉네임입니다.");
            }
            if (userRepository.existsByEmail(email)){
                throw new SignupEmailDuplicatedException("이미 사용중인 이메일입니다.");
            }
        }
    }
}

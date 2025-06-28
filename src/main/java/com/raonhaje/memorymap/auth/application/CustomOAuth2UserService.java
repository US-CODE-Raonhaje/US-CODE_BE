package com.raonhaje.memorymap.auth.application;

import com.raonhaje.memorymap.member.domain.Member;
import com.raonhaje.memorymap.member.repository.MemberJpaRepository;
import com.raonhaje.memorymap.auth.domain.CustomOAuth2User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberJpaRepository memberJpaRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");

        Member member = memberJpaRepository.findByEmail(email)
                .orElseGet(() -> memberJpaRepository.save(Member.create(email, name)));

        return new CustomOAuth2User(member, oAuth2User.getAttributes());
    }
}

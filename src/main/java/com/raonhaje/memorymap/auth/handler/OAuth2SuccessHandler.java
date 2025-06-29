package com.raonhaje.memorymap.auth.handler;

import com.raonhaje.memorymap.auth.application.RefreshTokenService;
import com.raonhaje.memorymap.auth.domain.CustomOAuth2User;
import com.raonhaje.memorymap.common.jwt.JwtProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final JwtProvider jwtTokenProvider;
    private final RefreshTokenService refreshTokenService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getEmail();

        String accessToken = jwtTokenProvider.createAccessToken(email);
        String refreshToken = jwtTokenProvider.createRefreshToken(email);

        refreshTokenService.saveOrUpdate(oAuth2User.getMember().getMemberId(), refreshToken);

        response.addHeader("Authorization", "Bearer " + accessToken);
        response.addHeader("Refresh-Token", refreshToken);

        response.setStatus(HttpServletResponse.SC_OK);
    }
}

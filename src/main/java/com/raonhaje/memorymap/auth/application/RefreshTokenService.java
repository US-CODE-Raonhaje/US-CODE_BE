package com.raonhaje.memorymap.auth.application;

import com.raonhaje.memorymap.auth.domain.RefreshToken;
import com.raonhaje.memorymap.auth.repository.RefreshTokenJpaRepository;
import com.raonhaje.memorymap.common.exception.BusinessException;
import com.raonhaje.memorymap.common.exception.ErrorCode;
import com.raonhaje.memorymap.common.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenJpaRepository refreshTokenJpaRepository;
    private final JwtProvider jwtProvider;

    @Transactional
    public void saveOrUpdate(Long memberId, String refreshTokenValue) {
        refreshTokenJpaRepository.findByMemberId(memberId)
                .map(token -> {
                    token.updateRefreshToken(refreshTokenValue);
                    return token;
                })
                .orElseGet(() -> refreshTokenJpaRepository.save(new RefreshToken(refreshTokenValue, memberId)));
    }

    @Transactional
    public String refreshAccessToken(String refreshToken) {
        refreshTokenJpaRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new BusinessException(ErrorCode.TOKEN_NOT_FOUND));

        String email = jwtProvider.getEmailFromToken(refreshToken);

        return jwtProvider.createAccessToken(email);
    }

    @Transactional
    public void deleteRefreshToken(String refreshToken) {
        RefreshToken token = refreshTokenJpaRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new BusinessException(ErrorCode.TOKEN_NOT_FOUND));

        refreshTokenJpaRepository.delete(token);
    }
}

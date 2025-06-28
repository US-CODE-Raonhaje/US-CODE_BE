package com.raonhaje.memorymap.auth.application;

import com.raonhaje.memorymap.auth.domain.RefreshToken;
import com.raonhaje.memorymap.auth.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken findById(Long memberId) {
        return refreshTokenRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Refresh token not found for member ID: " + memberId));
    }

    public void save(RefreshToken refreshToken) {
        refreshTokenRepository.save(refreshToken);
    }

    @Transactional
    public void deleteRefreshToken(Long kakaoId) {
        refreshTokenRepository.findById(kakaoId)
                .ifPresent(refreshTokenRepository::delete);
    }

}

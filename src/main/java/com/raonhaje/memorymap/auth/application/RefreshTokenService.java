package com.raonhaje.memorymap.auth.application;

import com.raonhaje.memorymap.auth.domain.RefreshToken;
import com.raonhaje.memorymap.auth.repository.RefreshTokenJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenJpaRepository refreshTokenJpaRepository;

    @Transactional
    public RefreshToken saveOrUpdate(Long memberId, String refreshTokenValue) {
        return refreshTokenJpaRepository.findByMemberId(memberId)
                .map(token -> {
                    token.updateRefreshToken(refreshTokenValue);
                    return token;
                })
                .orElseGet(() -> refreshTokenJpaRepository.save(new RefreshToken(refreshTokenValue, memberId)));
    }
}

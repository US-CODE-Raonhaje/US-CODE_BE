package com.raonhaje.memorymap.auth.repository;

import com.raonhaje.memorymap.auth.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenJpaRepository extends JpaRepository<RefreshToken, Long> {
}

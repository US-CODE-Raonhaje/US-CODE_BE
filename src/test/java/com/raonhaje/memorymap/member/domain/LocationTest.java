package com.raonhaje.memorymap.member.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationTest {

    @Test
    @DisplayName("Location 생성 테스트")
    void createLocationTest() {
        // given
        Double latitude = 37.5665;
        Double longitude = 126.9784;
        String address = "서울특별시 마포구";

        // when
        Location location = Location.create(latitude, longitude, address);
        
        // then
        assertEquals(latitude, location.getLatitude());
        assertEquals(longitude, location.getLongitude());
        assertEquals(address, location.getAddress());
    }

    @Test
    @DisplayName("Location 업데이트 테스트")
    void updateLocationTest() {
        // given
        Location location = Location.create(37.5665, 126.9784, "서울특별시 마포구");
        Double newLatitude = 37.1234;
        Double newLongitude = 127.5678;
        String newAddress = "강원도 춘천시";

        // when
        location.updateLocation(newLatitude, newLongitude, newAddress);

        // then
        assertEquals(newLatitude, location.getLatitude());
        assertEquals(newLongitude, location.getLongitude());
        assertEquals(newAddress, location.getAddress());
    }
}
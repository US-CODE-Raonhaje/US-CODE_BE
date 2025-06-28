package com.raonhaje.memorymap.member.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "location")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long locationId;

    private Double latitude;
    private Double longitude;

    private String address;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public static Location create(Double latitude, Double longitude, String address) {
        return Location.builder()
                .latitude(latitude)
                .longitude(longitude)
                .address(address)
                .build();
    }

    public void updateLocation(Double latitude, Double longitude, String address) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
    }
}

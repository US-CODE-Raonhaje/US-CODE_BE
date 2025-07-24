package com.raonhaje.memorymap.member.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "location")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(of = { "latitude", "longitude", "address" })
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long locationId;

    private Double latitude;
    private Double longitude;

    private String address;

    public static Location create(Double latitude, Double longitude, String address) {
        return new Location(null, latitude, longitude, address);
    }

    public void updateLocation(Double latitude, Double longitude, String address) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
    }
}

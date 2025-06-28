package com.raonhaje.memorymap.post.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Category {

    FUTURE("미래"),
    PRESENT("현재"),
    PAST("과거");

    private final String name;
}

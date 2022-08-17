package com.sparta.myblog.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostResponseDto {
    private String username;
    private String contents;
    private String title;
    private String password;
}

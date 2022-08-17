package com.sparta.myblog.dto;

import com.sparta.myblog.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostResponseDto {
    private boolean success;
    private Post data;
    private Object error;
}

package com.sparta.myblog.service;

import com.sparta.myblog.domain.Post;
import com.sparta.myblog.domain.PostRepository;
import com.sparta.myblog.domain.PostRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor // final 선언할때 스프링에게 알려줌
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional // 업데이트를 할 때, DB에 반영이 되는 것을 스프링에게 알려줌
    public Long update(Long id, PostRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 아이디입니다.")
        );
        post.update(requestDto);
        return post.getId();
    }
}

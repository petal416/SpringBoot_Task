package com.sparta.myblog.controller;

import com.sparta.myblog.entity.Post;
import com.sparta.myblog.repository.PostRepository;
import com.sparta.myblog.dto.PostRequestDto;
import com.sparta.myblog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostRepository postRepository;
    private final PostService postService;

    // 게시글 작성
    @PostMapping("/posts")
    public Post createPost(@RequestBody PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        return postRepository.save(post);
    }

//    // 게시글 비밀번호 확인
//    @PostMapping("/posts/{id}")
//    public Optional<Post> CheckPossword(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
//        Post post = postRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
//        );
//        return postRepository.findById(id);
//    }


    // 전체 게시글 목록 조회
    @GetMapping("/posts")
    public List<Post> getPostList() {
        return postRepository.findAllByOrderByModifiedAtDesc();
    }

    // 개별 게시글 조회
    @GetMapping("/posts/{id}")
    public Optional<Post> getPost(@PathVariable Long id) {
        return postRepository.findById(id);
    }

    // 게시글 삭제
    @DeleteMapping("/posts/{id}")
    public Long deletePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 글입니다.")
        );
        if (!post.getPassword().equals(requestDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        postRepository.deleteById(id);
        return id;
    }

    // 게시글 수정
    @PutMapping("/posts/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        postService.update(id, requestDto);
        return id;
    }
}

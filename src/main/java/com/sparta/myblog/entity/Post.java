package com.sparta.myblog.entity;

import com.sparta.myblog.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor // 기본생성자를 자동으로 만듭니다.
@Getter // setter는 repository에서 자동으로 해주기 때문에 설정 안 함
@Entity // 데이터베이스 기준으로 테이블 역할을 하는 것을 스프링에게 알려줌
public class Post extends Timestamped { // 생성,수정 시간을 자동으로 만들어줍니다.
    @GeneratedValue(strategy = GenerationType.AUTO) // id 자동증가명령
    @Id
    private Long id;

    @Column(nullable = false) // 컬럼 값이고 반드시 값이 존재해야 한다.
    private String username;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String password;


    // 생성자
    public Post(Long id, String username, String contents, String title, String password) {
        this.id = id;
        this.username = username;
        this.contents = contents;
        this.title = title;
        this.password = password;
    }

    public Post(PostRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.title = requestDto.getTitle();
        this.password = requestDto.getPassword();
    }

    public void update(PostRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.title = requestDto.getTitle();
        this.password = requestDto.getPassword();
    }
}
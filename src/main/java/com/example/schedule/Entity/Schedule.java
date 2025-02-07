package com.example.schedule.Entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "Schedule")
public class Schedule extends BaseEntity{
    @Id
    //기본키 생성
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //할일 제목
    @Column(nullable = false)
    private String todoTitle;

    //할일 내용
    @Column(nullable = false, columnDefinition = "longtext")
    private String todoContent;

    //작성 유저명(User테이블의 고유 식별자로 대체)
    @ManyToOne(fetch = FetchType.LAZY)//필요할 때만 User 정보를 가져올 수 있도록
    @JoinColumn(name = "User_id", nullable = false)
    private User user;

    public Schedule() {

    }

    public Schedule(String todoTitle, String todoContent){
        this.todoTitle=todoTitle;
        this.todoContent=todoContent;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

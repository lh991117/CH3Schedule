package com.example.schedule.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Schedule")
public class Schedule extends BaseEntity{
    @Id
    //기본키 생성
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //작성 유저명
    @Column(nullable = false)
    private String username;

    //할일 제목
    @Column(nullable = false)
    private String toooTitle;

    //할일 내용
    @Column(nullable = false, columnDefinition = "longtext")
    private String todo;

//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Long getId() {
//        return id;
//    }
}

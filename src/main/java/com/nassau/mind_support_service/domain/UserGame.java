package com.nassau.mind_support_service.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_users_game")
public class UserGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "nickname", length = 25, nullable = false)
    private String nickname;

    @Column(name = "icon", length = 50, nullable = false)
    private String icon;
}

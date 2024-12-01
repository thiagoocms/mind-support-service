package com.nassau.mind_support_service.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_responses")
public class Response {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vote_id", nullable = false)
    private Vote vote;

    @Column(name = "quest", nullable = false)
    private Integer quest;

    @Column(name = "response", nullable = false)
    private Integer response;
}

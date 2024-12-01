package com.nassau.mind_support_service.domain;

import com.nassau.mind_support_service.enumerated.VoteTypeEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_votes")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false)
    private Session session;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "type", nullable = false)
    private VoteTypeEnum type;


}

package com.nassau.mind_support_service.domain;

import com.nassau.mind_support_service.enumerated.VoteTypeEnum;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_votes")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false)
    private Session session;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "type", nullable = false)
    private VoteTypeEnum type;

    @OneToMany(mappedBy = "vote", fetch = FetchType.EAGER)
    private Set<Response> responses = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public VoteTypeEnum getType() {
        return type;
    }

    public void setType(VoteTypeEnum type) {
        this.type = type;
    }

    public Set<Response> getResponses() {
        return responses;
    }

    public void setResponses(Set<Response> responses) {
        this.responses = responses;
    }
}

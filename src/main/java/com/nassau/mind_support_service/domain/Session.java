package com.nassau.mind_support_service.domain;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "tb_sessions")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hash", length = 100, nullable = false)
    private String hash;

    @ManyToOne
    @JoinColumn(name = "class_id", nullable = false)
    private Class clazz;

    @OneToMany(mappedBy = "session", fetch = FetchType.LAZY)
    private Set<Vote> votes;

    public Session() {
    }

    public Session(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public Set<Vote> getVotes() {
        return votes;
    }

    public void setVotes(Set<Vote> votes) {
        this.votes = votes;
    }
}

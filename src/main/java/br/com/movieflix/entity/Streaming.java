package br.com.movieflix.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "streaming_tb")
public class Streaming {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    public Streaming(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Streaming() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

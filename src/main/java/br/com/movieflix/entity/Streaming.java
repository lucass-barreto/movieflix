package br.com.movieflix.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "streaming_tb")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Streaming {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

}

package umc.spring.domain;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class BaseEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}

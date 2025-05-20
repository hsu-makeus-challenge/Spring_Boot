package umc.spring.domain;

import jakarta.persistence.*;
import lombok.Getter;


@Getter
@MappedSuperclass
public abstract class BaseEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
}

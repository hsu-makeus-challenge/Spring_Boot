package umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String storeName;

    @Column(nullable = false, columnDefinition = "DOUBLE DEFAULT 0.0")
    private Double storeRating;

    @Column(nullable = false)
    private String storeAddress;

    @Column(columnDefinition = "TINYINT(1) DEFAULT 0", nullable = false)
    private Boolean isOpen; // 영업 여부
}

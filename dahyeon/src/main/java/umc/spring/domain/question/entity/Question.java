package umc.spring.domain.question.entity;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.spring.domain.question.entity.enums.QuestionType;
import umc.spring.global.common.BaseTimeEntity;
import umc.spring.global.converter.IntegerListConverter;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Question extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;
    private String title;
    private String content;
    private QuestionType questionType;
    @Convert(converter = IntegerListConverter.class)
    private List<Integer> photoList;
    private Boolean isAnswered;


}

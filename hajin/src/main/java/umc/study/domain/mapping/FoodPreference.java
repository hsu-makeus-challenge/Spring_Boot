package umc.study.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc.study.domain.Food;
import umc.study.domain.Users;
import umc.study.domain.common.BaseEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FoodPreference extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private Food food;

    public void setUser(Users user){
        //코드 재사용할 때 같이 쓰는거
        if(this.user != null)
            user.getFoodPreferenceList().remove(this);
        //(회원가입)지금 쓰는거
        this.user = user;
        user.getFoodPreferenceList().add(this);
    }

    public void setFoodCategory(Food foodCategory){
        this.food = foodCategory;
    }

}

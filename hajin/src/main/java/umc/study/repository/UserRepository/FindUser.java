package umc.study.repository.UserRepository;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import umc.study.domain.Users;

@Repository
public class FindUser {
    private EntityManager em;

    public Users findUserById(Long id) {
        return em.find(Users.class, id);
    }

}

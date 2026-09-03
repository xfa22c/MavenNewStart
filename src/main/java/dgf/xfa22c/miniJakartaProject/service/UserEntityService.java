package dgf.xfa22c.miniJakartaProject.service;

import dgf.xfa22c.miniJakartaProject.entities.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class UserEntityService {

    private final EntityManagerFactory emf;
    private final EntityManager em;

    public UserEntityService(){
        emf = Persistence.createEntityManagerFactory("myPU");
        em = emf.createEntityManager();
    }

    public void addUser(UserEntity user){
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    public List<UserEntity> listUsers() {
        return em.createQuery("SELECT u FROM userEntity u", UserEntity.class).getResultList();
    }

    @SuppressWarnings("unused")
    public void close() {
        em.close();
        emf.close();
    }
}

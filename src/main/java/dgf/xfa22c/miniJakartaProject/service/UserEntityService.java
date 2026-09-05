package dgf.xfa22c.miniJakartaProject.service;

import dgf.xfa22c.miniJakartaProject.dto.UserDTO;
import dgf.xfa22c.miniJakartaProject.entities.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
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

    @SuppressWarnings("unused")
    public void updateUser(UserDTO newUser, Long userId){
        em.getTransaction().begin();
        UserEntity user = em.find(UserEntity.class, userId);
        if (user == null){
            em.getTransaction().rollback();
            System.err.println("User is null");
            throw new IllegalArgumentException();
        }else{
            user.setName(newUser.getName());
            user.setEmail(newUser.getEmail());
            user.setAge(newUser.getAge());
            em.getTransaction().commit();
        }
    }

    public void deleteUser(Long id){
        em.getTransaction().begin();
        UserEntity user = em.find(UserEntity.class, id);
        if (user == null){
            em.getTransaction().rollback();
            System.err.println("User not found");
            throw new EntityNotFoundException();
        }else{
            em.remove(user);
        }
    }

    public UserEntity getUserByID(Long id){
        em.getTransaction().begin();
        UserEntity user = em.find(UserEntity.class, id);
        em.getTransaction().commit();
        return user;
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

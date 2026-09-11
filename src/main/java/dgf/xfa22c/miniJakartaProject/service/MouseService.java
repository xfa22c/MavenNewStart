package dgf.xfa22c.miniJakartaProject.service;

import dgf.xfa22c.miniJakartaProject.dto.MouseDTO;
import dgf.xfa22c.miniJakartaProject.entities.MouseEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class MouseService {

    private final EntityManagerFactory emf;
    private final EntityManager em;

    public MouseService(){
        emf = Persistence.createEntityManagerFactory("myPU");
        em = emf.createEntityManager();
    }

    public void addMouse(MouseEntity mouse){
        em.getTransaction().begin();
        em.persist(mouse);
        em.getTransaction().commit();
    }

    public void updateMouse(MouseDTO newMouse, Long id){
        em.getTransaction().begin();
        MouseEntity mouse = em.find(MouseEntity.class, id);
        if (mouse == null){
            System.err.println("Mouse not found, rollback (update)");
            em.getTransaction().rollback();
            throw new IllegalArgumentException();
        }else{
            mouse.setName(newMouse.getName());
            mouse.setSensor(newMouse.getSensor());
            mouse.setMaxAcceleration(newMouse.getMaxAcceleration());
            mouse.setPollingRate(newMouse.getPollingRate());
            em.getTransaction().commit();
        }
    }

    public void deleteMouse(Long id){
        em.getTransaction().begin();
        MouseEntity mouse = em.find(MouseEntity.class, id);
        if (mouse == null){
            System.err.println("Mouse not found, rollback (delete)");
            em.getTransaction().rollback();
            throw new IllegalArgumentException();
        }else{
            em.remove(mouse);
            em.getTransaction().commit();
        }
    }

    public MouseEntity getMouseById(Long id){
        return em.find(MouseEntity.class, id);
    }

    public List<MouseEntity> listMice(){
        return em.createQuery("SELECT m FROM MouseEntity m", MouseEntity.class).getResultList();
    }

    public void close(){
        emf.close();
        em.close();
    }

}

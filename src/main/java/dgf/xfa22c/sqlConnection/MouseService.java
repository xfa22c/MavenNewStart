package dgf.xfa22c.sqlConnection;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class MouseService {
    private final EntityManagerFactory emf;
    private final EntityManager em;

    MouseService(){
        emf = Persistence.createEntityManagerFactory("myPU");
        em = emf.createEntityManager();
    }


    //Add new mouse, requires Object of class Mouse
    @SuppressWarnings("unused")
    public void addMouse(Mouse mouse){
        em.getTransaction().begin();
        em.persist(mouse);
        em.getTransaction().commit();
        System.out.println("Mouse has been added");
    }

    //Update mouse. Requires (New)Object of class Mouse and ID
    @SuppressWarnings("unused")
    public void updateMouse(Mouse newMouse, Long id){
        em.getTransaction().begin();
        Mouse mouse = em.find(Mouse.class, id);
        if (mouse != null){
            mouse.setSensor(newMouse.getSensor());
            mouse.setPollingRate(newMouse.getPollingRate());
            mouse.setMaxAcceleration(newMouse.getMaxAcceleration());
            System.out.println("Mouse has been updated");
        }else{
            System.out.println("This mouse doesn't exists");
        }
        em.getTransaction().commit();
    }

    //Delete mouse, Requires only ID
    @SuppressWarnings("unused")
    public void deleteMouse(Long id){
        em.getTransaction().begin();
        Mouse mouse = em.find(Mouse.class, id);
        if (mouse != null){
            em.remove(mouse);
            System.out.println("Mouse has been deleted");
        }else{
            System.out.println("Mouse doesn't exists");
        }
        em.getTransaction().commit();
    }

    //List of mice, don't need any arguments
    @SuppressWarnings("unused")
    public void listMice(){
        List<Mouse> mice = em.createQuery("SELECT m FROM Mouse m", Mouse.class).getResultList();
        mice.forEach(System.out::println);
    }

    @SuppressWarnings("unused")
    public void close(){
        em.close();
        emf.close();
    }

}

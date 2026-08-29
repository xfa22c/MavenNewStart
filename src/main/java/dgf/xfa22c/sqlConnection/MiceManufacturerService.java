package dgf.xfa22c.sqlConnection;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class MiceManufacturerService {

    private final EntityManagerFactory emf;
    private final EntityManager em;

    MiceManufacturerService(){
        emf = Persistence.createEntityManagerFactory("myPU");
        em = emf.createEntityManager();
    }

    @SuppressWarnings("unused")
    public void addMouseManufacturer(MiceManufacturer manufacturer){
        em.getTransaction().begin();
        em.persist(manufacturer);
        em.getTransaction().commit();
        System.out.println("Manufacturer has been added");
    }

    @SuppressWarnings("unused")
    public void updateMouseManufacturer(Long manufacturerId, MiceManufacturer newMouseManufacturer){
        em.getTransaction().begin();
        MiceManufacturer manufacturer = em.find(MiceManufacturer.class, manufacturerId);
        if (manufacturer != null){
            manufacturer.setBrandName(newMouseManufacturer.getBrandName());
            em.getTransaction().commit();
        }else{
            em.getTransaction().rollback();
            System.err.println("Manufacturer not found");
        }
    }

    @SuppressWarnings("unused")
    public void deleteManufacturer(Long manufacturerId){
        em.getTransaction().begin();
        MiceManufacturer manufacturer = em.find(MiceManufacturer.class, manufacturerId);
        if (manufacturer != null){
            em.remove(manufacturer);
            em.getTransaction().commit();
        }else{
            em.getTransaction().rollback();
            System.out.println("Manufacturer == null");
        }

    }

    @SuppressWarnings("unused")
    public void listManufacturers(){
        List<MiceManufacturer> manufacturers =
                em.createQuery("SELECT m FROM MiceManufacturer m", MiceManufacturer.class).getResultList();
        manufacturers.forEach(m ->
                System.out.println("ID: " + m.getId() + " | Brand: " + m.getBrandName()));
    }

    @SuppressWarnings("unused")
    public void close(){
        em.close();
        emf.close();
    }

}

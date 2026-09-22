package dgf.xfa22c.miniJakartaProject.service;

import dgf.xfa22c.miniJakartaProject.dto.ManufacturerDTO;
import dgf.xfa22c.miniJakartaProject.dto.MouseDTO;
import dgf.xfa22c.miniJakartaProject.entities.ManufacturerEntity;
import dgf.xfa22c.miniJakartaProject.entities.MouseEntity;
import dgf.xfa22c.miniJakartaProject.enums.MouseTier;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.List;
import java.util.Set;

public class MiceService {

    private final EntityManagerFactory emf;
    private final EntityManager em;
    private final Validator validator;

    public MiceService(){
        emf = Persistence.createEntityManagerFactory("myPU");
        em = emf.createEntityManager();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    public void addMouse(MouseDTO mouseDTO){
        if (mouseDTO == null){
            System.err.println("Mouse DTO is null (add)");
            throw new IllegalArgumentException("Mouse DTO is null (add)");
        }else{
            Set<ConstraintViolation<MouseDTO>> violations = validator.validate(mouseDTO);
            if (!violations.isEmpty()){
                throw new IllegalArgumentException("Validation failed (add mouse) - " + buildMessage(violations));
            }
            em.getTransaction().begin();
            ManufacturerEntity manufacturer = em.find(ManufacturerEntity.class, mouseDTO.getManufacturerId());
            if (manufacturer != null){
                MouseEntity mouse = mouseDTO.toMouse();
                mouse.setManufacturer(manufacturer);
                mouse.setTier(determineTier(mouseDTO.getPrice()));
                em.persist(mouse);
                em.getTransaction().commit();
            }else{
                System.err.println("Manufacturer is null");
                em.getTransaction().rollback();
                throw new IllegalArgumentException("Manufacturer is null (Add mouse method)");
            }
        }
    }

    public void addManufacturer(ManufacturerDTO manufacturerDTO){
        if (manufacturerDTO == null){
            System.err.println("Manufacturer DTO is null (add)");
            throw new IllegalArgumentException("Manufacturer DTO is null (add method)");
        }else{
            Set<ConstraintViolation<ManufacturerDTO>> violations = validator.validate(manufacturerDTO);
            if (!violations.isEmpty()){
                throw new IllegalArgumentException("Validation failed (add manufacturer) - " + buildMessage(violations));
            }
            em.getTransaction().begin();
            ManufacturerEntity manufacturer = manufacturerDTO.toManufacturer();
            em.persist(manufacturer);
            em.getTransaction().commit();
        }
    }

    public void updateMouse(MouseDTO dto, Long id){
        if (dto == null){
            System.err.println("Mouse DTO is null (update)");
            throw new IllegalArgumentException("Mouse DTO is null (update)");
        }else {
            Set<ConstraintViolation<MouseDTO>> violations = validator.validate(dto);
            if (!violations.isEmpty()){
                throw new IllegalArgumentException("Validation failed (edit mouse) - " + buildMessage(violations));
            }
            em.getTransaction().begin();
            MouseEntity mouse = em.find(MouseEntity.class, id);
            if (mouse == null) {
                System.err.println("Mouse not found, rollback (update)");
                em.getTransaction().rollback();
                throw new IllegalArgumentException("Mouse not found (update)");
            } else {
                mouse.setName(dto.getName());
                mouse.setSensor(dto.getSensor());
                mouse.setMaxAcceleration(dto.getMaxAcceleration());
                mouse.setPollingRate(dto.getPollingRate());
                mouse.setPrice(dto.getPrice());
                mouse.setTier(determineTier(dto.getPrice()));
                em.getTransaction().commit();
            }
        }
    }

    public void updateManufacturer(ManufacturerDTO dto, Long id){
        if (dto == null){
            System.err.println("Manufacturer DTO is null");
            throw new IllegalArgumentException("Manufacturer DTO is null (update method)");
        }else{
            Set<ConstraintViolation<ManufacturerDTO>> violations = validator.validate(dto);
            if (!violations.isEmpty()){
                throw new IllegalArgumentException("Validation failed (edit manufacturer) - " + buildMessage(violations));
            }
            em.getTransaction().begin();
            ManufacturerEntity manufacturer = em.find(ManufacturerEntity.class, id);
            if (manufacturer == null){
                System.err.println("Manufacturer not found (update)");
                em.getTransaction().rollback();
                throw new IllegalArgumentException("Manufacturer not found (update)");
            }else{
                manufacturer.setName(dto.getName());
                manufacturer.setYearOfCreation(dto.getYearOfCreation());
                em.getTransaction().commit();
            }
        }
    }

    public void deleteMouse(Long id){
        em.getTransaction().begin();
        MouseEntity mouse = em.find(MouseEntity.class, id);
        if (mouse == null){
            System.err.println("Mouse not found, rollback (delete)");
            em.getTransaction().rollback();
            throw new IllegalArgumentException("Mouse not found (delete)");
        }else{
            em.remove(mouse);
            em.getTransaction().commit();
        }
    }

    public void deleteManufacturer(Long id){
        em.getTransaction().begin();
        ManufacturerEntity manufacturer = em.find(ManufacturerEntity.class, id);
        if (manufacturer == null){
            System.err.println("Manufacturer not found (delete)");
            em.getTransaction().rollback();
            throw new IllegalArgumentException("Manufacturer not found (delete)");
        }else{
            em.remove(manufacturer);
            em.getTransaction().commit();
        }
    }


    private MouseTier determineTier(int price){
        if (price < 1) throw new IllegalArgumentException("Price must be positive");
        if (price >= 100) return MouseTier.EXPENSIVE;
        if (price >= 30) return MouseTier.MID_RANGE;
        if (price >= 10) return MouseTier.BUDGET;
        return MouseTier.CHEAP;
    }

    public MouseEntity getMouseById(Long id){
        return em.find(MouseEntity.class, id);
    }

    public ManufacturerEntity getManufacturerById(Long id){
        return em.find(ManufacturerEntity.class, id);
    }

    public List<MouseEntity> listMice(){
        return em.createQuery("SELECT mo FROM MouseEntity mo", MouseEntity.class).getResultList();
    }

    public List<ManufacturerEntity> listManufacturers(){
        return em.createQuery("SELECT man FROM ManufacturerEntity man", ManufacturerEntity.class).getResultList();
    }

    private String buildMessage(Set<? extends ConstraintViolation<?>> violations){
        StringBuilder sb = new StringBuilder();
        for (ConstraintViolation<?> v: violations){
            sb.append(v.getPropertyPath())
                    .append(": ")
                    .append(v.getMessage())
                    .append("; ");
        }
        return sb.toString();
    }

    @SuppressWarnings("unused")
    public void close(){
        em.close();
        emf.close();
    }

}

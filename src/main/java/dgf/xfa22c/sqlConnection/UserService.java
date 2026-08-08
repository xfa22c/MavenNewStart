package dgf.xfa22c.sqlConnection;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class UserService {

    private final EntityManagerFactory emf;
    private final EntityManager em;

    public UserService() {
        emf = Persistence.createEntityManagerFactory("myPU");
        em = emf.createEntityManager();
    }

    //Add User, only Name needed
    //Suppress Warnings cause - You can not use every method in same time
    @SuppressWarnings("unused")
    public void addUser(String name){

        em.getTransaction().begin();
        User user = new User();
        user.setName(name);
        em.persist(user);
        em.getTransaction().commit();
        System.out.println("User Added - " + name);

    }

    //Update user, ID needed cause - Without ID you won't find the user
    //Suppress Warnings cause - You can not use every method in same time
    @SuppressWarnings("unused")
    public void updateUser(Long id, String newName){
        em.getTransaction().begin();
        User user = em.find(User.class, id);
            if (user != null){
                user.setName(newName);
                em.merge(user);
                System.out.println("Updated User");
            }else{
                System.out.println("User not Found");
            }
            em.getTransaction().commit();
    }

    //Delete user, only ID needed, cause - Without ID you won't find the user
    //Suppress Warnings cause - You can not use every method in same time
    @SuppressWarnings("unused")
    public void deleteUser(Long id){
        em.getTransaction().begin();
        User user = em.find(User.class, id);
        if (user !=null){
            em.remove(user);
            System.err.println("User Deleted");
        }else {
            System.out.println("User not Found");
        }
        em.getTransaction().commit();
    }

    //List of All Users, don't needed any arguments
    //Suppress Warnings cause - You can not use every method in same time
    @SuppressWarnings("unused")
    public void listUsers(){
        List<User> users = em.createQuery("SELECT u FROM User u", User.class).getResultList();
        if (users.isEmpty()){
            System.out.println("Users list is Empty");
        }else {
            users.forEach(u -> System.out.println("ID - " + u.getId() + ", Name - " + u.getName()));
        }
    }

    //Necessary method to close Entity Manager and Entity Manager Factory
    @SuppressWarnings("unused")
    public void close(){
        em.close();
        emf.close();
    }

}

package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.entity.Artist;

import java.util.List;

public class Main {
    public static void main(String[] args) {
//
//        EntityManagerFactory emf =
//                Persistence.createEntityManagerFactory("ExamplePU");
//        EntityManager em = emf.createEntityManager();
//
//        em.getTransaction().begin();
//        //Artist artist = new Artist("Beatles");
//        //em.persist(artist);
//
//        Artist a = (Artist)em.createQuery(
//                        "select e from Artist e where e.name='Beatles'")
//                .getSingleResult();
//        //a.setName("The Beatles");
//
//        em.getTransaction().commit();
//        em.close();
//        emf.close();

        ArtistRepository artistRepository = new ArtistRepository();
        //Artist selena = new Artist("Selena Gomez");
        //artistRepository.create(selena);
        Artist artist = artistRepository.findById(1);
        System.out.println(artist.getName());
        List<Artist> artist2 = artistRepository.findByName("Zendaya");
        for (Artist artist3 : artist2) {
            System.out.println(artist3.getName());
        }
        List<Artist> artist4 = artistRepository.findAll();
        for (Artist artist5 : artist4) {
            System.out.println(artist5.getName()+" "+artist5.getId());
        }
        PersistenceManager.getEntityManager().close();

    }
}

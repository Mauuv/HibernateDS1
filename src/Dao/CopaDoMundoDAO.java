package Dao;

import Entities.CopaDoMundo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Optional;

public class CopaDoMundoDAO {
    private static final String PERSISTENCE_UNIT = "AtividadePratica";

    private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        return factory.createEntityManager();
    }

    public Optional<List<CopaDoMundo>> buscaTodos() {
        try (var em = getEntityManager()) {
            String sql = "SELECT ano, paisSede, campeao, viceCampeao, terceiroColocado, quartoColocado FROM CopaDoMundo c";
            var query = em.createQuery(sql, CopaDoMundo.class);
            return Optional.ofNullable(query.getResultList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean insereCopa(CopaDoMundo copa) {
        var em = getEntityManager();
        try {
            em.getTransaction().begin();

            em.persist(copa);

            em.getTransaction().commit();

            em.close();

            return true;
        } catch (Exception e) {

            em.getTransaction().rollback();

            throw new RuntimeException(e);
        }
    }

    public Optional<List<CopaDoMundo>> buscaPorPais(String pais) {
        try (var em = getEntityManager()) {
            String sql = "SELECT ano, paisSede, campeao, viceCampeao, terceiroColocado, quartoColocado FROM CopaDoMundo WHERE campeao = :pais or viceCampeao = :pais or terceiroColocado = :pais or quartoColocado = :pais";
            var query = em.createQuery(sql, CopaDoMundo.class).setParameter("pais", pais);
            return Optional.ofNullable(query.getResultList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
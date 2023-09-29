package Dao;

import Entities.CopaDoMundo;
import Entities.FinalCopaDoMundo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Optional;

public class FinalCopaDoMundoDAO {
    private static final String PERSISTENCE_UNIT = "AtividadePratica";
    private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        return factory.createEntityManager();
    }

    public Optional<List<FinalCopaDoMundo>> buscaPorAno(CopaDoMundo copa) {

        try(var em = getEntityManager()) {
            CriteriaBuilder criterio = em.getCriteriaBuilder();

            CriteriaQuery<FinalCopaDoMundo> sql = criterio.createQuery(FinalCopaDoMundo.class);
            Root<FinalCopaDoMundo> raiz = sql.from(FinalCopaDoMundo.class);
            sql.select(raiz).where(criterio.equal(raiz.get("copaDoMundo"), copa));

            Query query = em.createQuery(sql);
            query.setMaxResults(1);
            return Optional.ofNullable(query.getResultList());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public boolean insereFinalCopa(FinalCopaDoMundo finalCopa) {
        var em = getEntityManager();
        if (verificaSeExiste(finalCopa)) {
            return false;
        } else {
            try {
                em.getTransaction().begin();

                em.persist(finalCopa);

                em.getTransaction().commit();

                em.close();

                return true;
            } catch (Exception e) {

                em.getTransaction().rollback();

                throw new RuntimeException(e);
            }
        }
    }

    public boolean verificaSeExiste(FinalCopaDoMundo finalCopa) {
        FinalCopaDoMundoDAO finalCopaDoMundoDAO = new FinalCopaDoMundoDAO();
        int anoCopa = finalCopa.getCopaDoMundo().getAno();

        return !finalCopaDoMundoDAO.buscaPorAno(new CopaDoMundo(anoCopa)).get().isEmpty();
    }

    public Long geraId() {
        try (var em = getEntityManager()) {
            String sql = "SELECT coalesce(max(id) , 0) + 1  FROM FinalCopaDoMundo";
            var query = em.createQuery(sql, Long.class);
            return query.getResultList().get(0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

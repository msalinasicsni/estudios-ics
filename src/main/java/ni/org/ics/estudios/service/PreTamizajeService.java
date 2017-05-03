package ni.org.ics.estudios.service;

import ni.org.ics.estudios.domain.PreTamizaje;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Miguel Salinas on 5/2/2017.
 * V1.0
 */
@Service("preTamizajeService")
@Transactional
public class PreTamizajeService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    public List<PreTamizaje> getPreTamizajes()
    {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from PreTamizaje ");
        return query.list();
    }

    public PreTamizaje getPreTamizajeByCodigo(String codigo)
    {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from PreTamizaje where codigo = :codigo");
        query.setParameter("codigo",codigo);
        return (PreTamizaje)query.uniqueResult();
    }

    public void saveOrUpdate(PreTamizaje preTamizaje)
    {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(preTamizaje);
    }
}
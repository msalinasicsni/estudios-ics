package ni.org.ics.estudios.service.cohortefamilia;

import ni.org.ics.estudios.domain.cohortefamilia.encuestas.EncuestaParticipante;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Miguel Salinas on 5/4/2017.
 * V1.0
 */
@Transactional
@Service("encuestaParticipanteService")
public class EncuestaParticipanteService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    public List<EncuestaParticipante> getEncuestasParticipantes(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from EncuestaParticipante ");
        return query.list();
    }
    public List<EncuestaParticipante> getEncuestasParticipantesByUser(String username){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from EncuestaParticipante ec where ec.pasive = false and ec.participante.casa.codigo in (" +
                "select cc.tamizaje.participante.casa.codigo from CartaConsentimiento cc where cc.estudio.codigo in (" +
                " select us.estudio.codigo from UserStudy us where us.usuario.username = :username))");
        query.setParameter("username",username);
        return query.list();
    }

    public EncuestaParticipante getEncuestaParticipanteByCodigo(Integer codigo){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from EncuestaParticipante where participante.codigo = :codigo");
        query.setParameter("codigo", codigo);
        return (EncuestaParticipante)query.uniqueResult();
    }

    public void saveOrUpdate(EncuestaParticipante encuestaParticipante){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(encuestaParticipante);
    }
}
package ar.edu.unq.tip.marchionnelattenero.repositories;

import ar.edu.unq.tip.marchionnelattenero.models.UserModel;
import ar.edu.unq.tip.marchionnelattenero.repositories.utils.GenericRepository;
import ar.edu.unq.tip.marchionnelattenero.repositories.utils.HibernateGenericDAO;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("userModelRepository")
public class UserModelRepository extends HibernateGenericDAO<UserModel> implements GenericRepository<UserModel> {

    private static final long serialVersionUID = 1L;

    @Override
    protected Class<UserModel> getDomainClass() {
        return UserModel.class;
    }

    public UserModel findByUserId(String userId) {
        Criteria cr = this.getSession().createCriteria(this.getDomainClass());
        cr.add(Restrictions.eq("userId", userId));
        return (UserModel) cr.uniqueResult();
    }
}

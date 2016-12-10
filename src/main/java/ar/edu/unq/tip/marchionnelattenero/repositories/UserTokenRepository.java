package ar.edu.unq.tip.marchionnelattenero.repositories;

import ar.edu.unq.tip.marchionnelattenero.models.UserModel;
import ar.edu.unq.tip.marchionnelattenero.models.UserToken;
import ar.edu.unq.tip.marchionnelattenero.repositories.utils.GenericRepository;
import ar.edu.unq.tip.marchionnelattenero.repositories.utils.HibernateGenericDAO;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("userTokenRepository")
public class UserTokenRepository extends HibernateGenericDAO<UserToken> implements GenericRepository<UserToken> {

    @Override
    protected Class<UserToken> getDomainClass() {
        return UserToken.class;
    }

    public UserToken findByUserToken(String token) {
        Criteria cr = this.getSession().createCriteria(this.getDomainClass());
        cr.add(Restrictions.eq("token", token));
        return (UserToken) cr.uniqueResult();
    }

    public UserToken findByUser(UserModel userModel) {
        Criteria cr = this.getSession().createCriteria(this.getDomainClass());
        cr.add(Restrictions.eq("userModel", userModel));
        return (UserToken) cr.uniqueResult();
    }
}

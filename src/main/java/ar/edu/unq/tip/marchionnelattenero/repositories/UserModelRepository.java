package ar.edu.unq.tip.marchionnelattenero.repositories;

import ar.edu.unq.tip.marchionnelattenero.models.UserModel;
import ar.edu.unq.tip.marchionnelattenero.repositories.utils.GenericRepository;
import ar.edu.unq.tip.marchionnelattenero.repositories.utils.HibernateGenericDAO;
import org.springframework.stereotype.Repository;


@Repository("userModelRepository")
public class UserModelRepository extends HibernateGenericDAO<UserModel> implements GenericRepository<UserModel> {

    private static final long serialVersionUID = 1L;

    @Override
    protected Class<UserModel> getDomainClass() {
        return UserModel.class;
    }

}

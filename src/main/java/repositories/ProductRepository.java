package repositories;

import model.Product;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import repositories.utils.GenericRepository;
import repositories.utils.HibernateGenericDAO;


@Repository("productRepository")
public class ProductRepository extends HibernateGenericDAO<Product> implements GenericRepository<Product> {

    private static final long serialVersionUID = -4036325633305672220L;

    @Override
    protected Class<Product> getDomainClass() {
        return Product.class;
    }

    public Product findByname(String name){
        Criteria criteria = getSession().createCriteria(Product.class);
        Product yourObject = (Product) criteria.add(Restrictions.eq("name", name)).uniqueResult();
        return yourObject;
    }

    public Product findByProductName(String name) {
        Criteria cr = this.getSession().createCriteria(this.getDomainClass());
        cr.add(Restrictions.eq("name", name));
        return (Product) cr.uniqueResult();
    }
}

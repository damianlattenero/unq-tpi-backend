package ar.edu.unq.tip.ml.repositories;

import ar.edu.unq.tip.ml.models.Cache;
import ar.edu.unq.tip.ml.models.Product;
import ar.edu.unq.tip.ml.repositories.utils.GenericRepository;
import ar.edu.unq.tip.ml.repositories.utils.HibernateGenericDAO;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

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

    @Override
    public void save(Product product){
        super.save(product);
        Cache.getInstance().addNewProduct(product.getId());
    }

}
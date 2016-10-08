package ar.edu.unq.tip.marchionnelattenero.controllers;


import ar.edu.unq.tip.marchionnelattenero.controllers.requests.ProductCreationBody;
import ar.edu.unq.tip.marchionnelattenero.controllers.responses.ProductCreationResponse;
import ar.edu.unq.tip.marchionnelattenero.factories.ProductFactory;
import ar.edu.unq.tip.marchionnelattenero.models.Product;
import ar.edu.unq.tip.marchionnelattenero.repositories.ProductRepository;
import ar.edu.unq.tip.marchionnelattenero.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.ws.rs.*;
import java.util.List;

@Path("products")
@Controller("productController")
public class ProductController {

    private ProductService productService;
    private ProductFactory productFactory;
    @Autowired
    private ProductRepository productRepository;

    @GET
    @Path("all")
    @Produces("application/json")
    public List<ProductCreationResponse> getAll() {
        return ProductCreationResponse.buildMany(this.productRepository.findAll());
    }

    @POST
    @Path("create")
    @Consumes("application/json")
    @Produces("application/json")
    public ProductCreationResponse create(ProductCreationBody productBody) {
        Product product = this.productService.createProduct(productBody.getName(), productBody.getDescription());
        return ProductCreationResponse.build(product);
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public ProductCreationResponse findProductsByID(@PathParam("id") Integer id) {
        Product productFound = this.getProductFactory().getProductByID(id);
        return ProductCreationResponse.build(productFound);
    }

    @PostConstruct
    public void loadData() {
        this.getProductFactory().createBasicProducts();
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setProductFactory(ProductFactory productFactory) {
        this.productFactory = productFactory;
    }

    public ProductFactory getProductFactory() {
        return productFactory;
    }

    public ProductService getProductService() {
        return productService;
    }

}

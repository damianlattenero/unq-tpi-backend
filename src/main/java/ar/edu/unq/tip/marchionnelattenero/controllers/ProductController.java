package ar.edu.unq.tip.marchionnelattenero.controllers;


import ar.edu.unq.tip.marchionnelattenero.controllers.requests.ProductCreationBody;
import ar.edu.unq.tip.marchionnelattenero.controllers.requests.ProductStockBody;
import ar.edu.unq.tip.marchionnelattenero.controllers.responses.ProductCreationResponse;
import ar.edu.unq.tip.marchionnelattenero.controllers.responses.ProductStockResponse;
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

    @Autowired
    private ProductService productService;

    @Autowired
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
    @Path("modifyStock")
    @Consumes("application/json")
    @Produces("application/json")
    public ProductStockResponse create(ProductStockBody body) {
        Product product = this.productRepository.findById(body.getProductId());
        product.setHasStock(body.getHasStock());
        this.productRepository.update(product);
        product = (this.productRepository.findById(body.getProductId()));
        return ProductStockResponse.build(product);
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
        Product productFound = this.productFactory.getProductByID(id);
        return ProductCreationResponse.build(productFound);
    }

    @PostConstruct
    public void loadData() {
        this.productFactory.createBasicProducts();
    }

}

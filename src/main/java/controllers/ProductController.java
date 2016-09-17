package controllers;


import factories.ProductFactory;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import request.ProductCreationBody;
import responses.ProductCreationResponse;
import service.ProductService;

import javax.annotation.PostConstruct;
import javax.ws.rs.*;
import java.util.List;

@Path("products")
@Controller("productController")
public class ProductController {

    private ProductService productService;
    private ProductFactory productFactory;

    @GET
    @Path("all")
    @Produces("application/json")
    public List<Product> getAll() {
        return this.getProductService().findAll();
    }

    @POST
    @Path("create")
    @Consumes("application/json")
    @Produces("application/json")
    public ProductCreationResponse create(ProductCreationBody productBody) {
        Product product = this.productService.createProduct(productBody.getProductName(), productBody.getProductDescription());
        return ProductCreationResponse.build(product);
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public Product findProductsByID(@PathParam("id") Integer id) {
        Product productFound = this.getProductFactory().getProductByID(id);
        return productFound;
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

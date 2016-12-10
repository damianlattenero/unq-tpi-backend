package ar.edu.unq.tip.marchionnelattenero.controllers.responses;


import ar.edu.unq.tip.marchionnelattenero.models.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductCreationResponse {

    private String name;
    private String description;
    private Integer id;
    //    private Integer pending;
    private Boolean hasStock;

    public ProductCreationResponse(Integer id, String name, String description, Boolean hasStock) {
        this.setId(id);
        this.setName(name);
        this.setDescription(description);
        this.setHasStock(hasStock);
//        this.setPending(Cache.getInstance().getProductPending(this.getId()));
    }

    public static ProductCreationResponse build(Product product) {
        return new ProductCreationResponse(product.getId(), product.getName(), product.getDescription(), product.getHasStock());
    }

    public static List<ProductCreationResponse> buildMany(List<Product> all) {
        return all.stream().map(ProductCreationResponse::build).collect(Collectors.toList());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getHasStock() {
        return hasStock;
    }

    public void setHasStock(Boolean hasStock) {
        this.hasStock = hasStock;
    }

/*    public Integer getPending() {
        return pending;
    }

    public void setPending(Integer pending) {
        this.pending = pending;
    }*/
}

package ar.edu.unq.tip.marchionnelattenero.controllers.responses;


import ar.edu.unq.tip.marchionnelattenero.models.Cache;
import ar.edu.unq.tip.marchionnelattenero.models.Product;

public class ProductCreationResponse {

    private String name;
    private String description;
    private Integer id;
    private Integer pending;


    public ProductCreationResponse(String name, String description, Integer id) {
        this.setName(name);
        this.setDescription(description);
        this.setId(id);
        this.setPending(Cache.getInstance().getPending(this.getId()));
    }


    public static ProductCreationResponse build(Product product) {
        return new ProductCreationResponse(product.getName(), product.getDescription(), product.getId());
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

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPending() { return pending; }

    public void setPending(Integer pending) { this.pending = pending; }
}

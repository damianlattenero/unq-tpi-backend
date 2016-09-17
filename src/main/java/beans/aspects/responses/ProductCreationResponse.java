package beans.aspects.responses;


import beans.aspects.model.Product;

public class ProductCreationResponse {

    private String name;
    private String description;
    private Integer id;


    public ProductCreationResponse(String name, String description, Integer id) {
        this.name = name;
        this.description = description;
        this.id = id;
    }


    public static ProductCreationResponse build(Product product) {
        return new ProductCreationResponse(product.getName(), product.getDescription(), product.getId());
    }

}

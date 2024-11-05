package dev.rohit.productservice.repositories.projections;

public interface ProductWithTitleAndId {
    Long getId();
    String getTitle();

    String getDescription();
}

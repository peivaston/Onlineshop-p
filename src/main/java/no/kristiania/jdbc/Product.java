package no.kristiania.jdbc;

import java.util.Objects;

public class Product {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);

    }

    @Override
    public String toString() {
        return "Products{" +
                "name='" + name + '\'' +
                '}';
    }
}

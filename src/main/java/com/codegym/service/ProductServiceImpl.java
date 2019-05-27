package com.codegym.service;

import com.codegym.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductServiceImpl implements ProductService {
    private static Map<Integer, Product> products;
    private static int key;

    public static int getKey() {
        return key;
    }

    public static void setKey(int key) {
        ProductServiceImpl.key = key;
    }

    static {
        products = new HashMap<>();
        products.put(1, new Product(1,"Camry",20000,"sedan","Toyota"));
        products.put(2, new Product(2,"Ranger",10000,"pick-up","Ford"));
        products.put(3, new Product(3,"RangerRover",50000,"SUV","LandRover"));
    }

    static {
        key = products.size();
    }
    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public void save(Product product) {
        products.put(product.getId(),product);
    }

    @Override
    public Product findById(int id) {
        return products.get(id);
    }

    @Override
    public void update(int id, Product product) {
        products.replace(id, product);
    }

    @Override
    public void remove(int id) {
        products.remove(id);
    }
}

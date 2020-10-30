/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import exceptions.IncorrectValueException;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author pupil
 */
public class Product implements Serializable{

//variables
    private String name;
    private double price;
    private int quantity;

//constructors    
    public Product(){
    }
    public Product(String name, double price, int quantity) throws IncorrectValueException{
        this.setName(name);
        this.setPrice(price);
        this.setQuantity(quantity);
    }

//getters
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public int getQuantity() {
        return quantity;
    }
    
//setters
    public void setName(String name) throws IncorrectValueException {
        if(name.trim().equals("")){
            throw new IncorrectValueException("Неверно введено название");
        };
        this.name = name;       
    }
    public void setPrice(double price) throws IncorrectValueException {
        if(price <= 0){
            throw new IncorrectValueException("Неверно введена цена");
        }
        this.price = price;
    }
    public void setQuantity(int quantity) throws IncorrectValueException {
        if(quantity <= 0){
            throw new IncorrectValueException("Неверно введено количество");
        }
        this.quantity = quantity;
    }
       
//to string
    @Override
    public String toString() {
        return "Product{" + "model=" + name + ", cost=" + price + ", quantity=" + quantity + '}';
    }
    public String getData() {
        return "Product{" + "model=" + name + ", cost=" + price + "}";
    }
    
    
//hash code    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.name);
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
        hash = 89 * hash + this.quantity;
        return hash;
    }

//equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (Double.doubleToLongBits(this.price) != Double.doubleToLongBits(other.price)) {
            return false;
        }
        if (this.quantity != other.quantity) {
            return false;
        }
        return true;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import com.sun.istack.internal.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author pupil
 */
@Entity
@NamedQueries({
 @NamedQuery(name = "loadDeals", query = "SELECT * FROM Deal"),
 @NamedQuery(name = "getLastDeal", query = "SELECT * FROM Deal ORDER BY Date DESC LIMIT 1"),
})
public class Deal implements Serializable{
    
//variables
    @Id @GeneratedValue
    private int id;
    @NotNull
    private User user;
    @NotNull
    private Product product;
    @NotNull
    private int quantity;
    @NotNull
    private Date date;
    
//constructors
    public Deal(){
    }
    public Deal(User user, Product product){
        this.user = user;
        this.product = product;
        this.quantity = 1;
        this.date = new Date();
    }
    public Deal(User user, Product product, int quantity){
        this.user = user;
        this.product = product;
        this.quantity = quantity;
        this.date = new Date();
    }
    
//getters
    public int getId() {
        return id;
    }
    public User getUser() {
        return user;
    }
    public Product getProduct() {
        return product;
    }
    public int getQuantity() {
        return quantity;
    }
    public Date getDate() {
        return date;
    }

//setters
    /*
    Данные делки нельзя изменить
    */
 
//to string

    @Override
    public String toString() {
        return "Deal{" + "user=" + user + ", product=" + product.getData() + ", quantity=" + quantity + ", date=" + date + '}';
    }
    
    
//hash code
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.user);
        hash = 29 * hash + Objects.hashCode(this.product);
        hash = 29 * hash + this.quantity;
        hash = 29 * hash + Objects.hashCode(this.date);
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
        final Deal other = (Deal) obj;
        if (this.quantity != other.quantity) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.product, other.product)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }
    
}

/*package com.driver.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Admin{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;


   // @OneToMany(mappedBy = "admin",cascade = CascadeType.ALL)
   // private List<Customer> customerList;

    //@OneToMany(mappedBy = "admin",cascade = CascadeType.ALL)
    //private List<Driver> driverList;

    public int getAdminId() {
        return id;
    }

    public void setAdminId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}*/

package com.driver.model;

import javax.persistence.*;

@Entity
@Table(name = "Admin")

public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adminId;
    private String username;
    private String password;

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAdminId() {
        return adminId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Admin(int adminId, String username, String password) {
        this.adminId = adminId;
        this.username = username;
        this.password = password;
    }

    public Admin() {
    }


}

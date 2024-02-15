package com.seyhavorn.springbootecommerce.shop.entity;

import com.seyhavorn.springbootecommerce.authentication.entity.User;
import com.seyhavorn.springbootecommerce.helper.AuditableEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "shop")
@RequiredArgsConstructor
public class Shop extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String contact;
    @Email(message = "Invalid email")
    private String email;
    private String logo;
    private String description;
    @Column(columnDefinition = "jsonb")
    private String shop_object;

    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @ToString.Exclude
    private Set<Branch> branches = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "shop_user",
            joinColumns = {@JoinColumn(name = "shop_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")})
    private Set<User> users = new HashSet<>();

    public void assignUserToShop(User user) {
        this.users.add(user);
        user.getShops().add(this);
    }

    public void removeUserFromShop(User user) {
        this.users.remove(user);
        user.getShops().remove(this);
    }
}

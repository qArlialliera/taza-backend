package com.petiveriaalliacea.taza.entities;

import com.petiveriaalliacea.taza.entities.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;

import static com.petiveriaalliacea.taza.utils.Constants.DATABASE_PREFIX;
import static jakarta.persistence.FetchType.EAGER;

@Table(name = DATABASE_PREFIX + "request")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Request extends BaseEntity<Long> {
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    @ManyToMany(fetch = EAGER)
    private Collection<Category> categories = new ArrayList<>();
    private String remarks;
    private String categorySuggest;
}

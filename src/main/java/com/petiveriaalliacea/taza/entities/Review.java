package com.petiveriaalliacea.taza.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.petiveriaalliacea.taza.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;

import static com.petiveriaalliacea.taza.utils.Constants.DATABASE_PREFIX;
import static jakarta.persistence.FetchType.EAGER;

@Table(name = DATABASE_PREFIX + "review")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review extends BaseEntity<Long> {
    @JsonBackReference(value="user_review")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @JsonBackReference(value="review_company")
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    private double rate;
    @JsonManagedReference(value="review_comment")
    @OneToMany(mappedBy = "review")
    private Collection<Comment> comments = new ArrayList<>();
}

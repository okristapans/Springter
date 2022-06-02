package com.springter.business.repository.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@ToString
@Table(name = "posts")
public class PostDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "body")
    private String body;

    public PostDAO() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PostDAO postDAO = (PostDAO) o;
        return id != null && Objects.equals(id, postDAO.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}

package com.sheennae.serious.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "bias")
public class BiasModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private int id;


    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private BiasType type;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BiasType getType() {
        return type;
    }

    public void setType(BiasType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BiasModel biasModel = (BiasModel) o;
        return id == biasModel.id &&
                type == biasModel.type;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, type);
    }

    @Override
    public String toString() {
        return "BiasModel{" +
                "id=" + id +
                ", type=" + type +
                '}';
    }


}

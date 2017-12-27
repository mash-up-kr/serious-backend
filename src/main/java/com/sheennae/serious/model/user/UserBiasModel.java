package com.sheennae.serious.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_bias")
public class UserBiasModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "bias", nullable = false)
    private UserBias bias;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserBias getBias() {
        return bias;
    }

    public void setBias(UserBias bias) {
        this.bias = bias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserBiasModel userBiasModel = (UserBiasModel) o;
        return id == userBiasModel.id &&
                bias == userBiasModel.bias;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, bias);
    }

    @Override
    public String toString() {
        return "UserBiasModel{" +
                "id=" + id +
                ", bias=" + bias +
                '}';
    }

}

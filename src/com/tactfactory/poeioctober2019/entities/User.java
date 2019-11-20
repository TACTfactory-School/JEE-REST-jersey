package com.tactfactory.poeioctober2019.entities;

public class User extends EntityBase {

    public String firstname;

    public String lastname;

    public String displayName() {
        return String.format("%s %s", this.firstname, this.lastname);
    }
}

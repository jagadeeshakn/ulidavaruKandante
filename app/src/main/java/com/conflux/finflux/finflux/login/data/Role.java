package com.conflux.finflux.finflux.login.data;

import com.conflux.finflux.finflux.db.LoginUserRole;

/**
 * Created by Praveen J U on 6/29/2016.
 */
public class Role extends LoginUserRole{
    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

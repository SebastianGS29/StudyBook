package com.example.admin_login_backend.entity;

public class DisabledUser {
    private String career;
    private String cicle;
    private String code;
    private boolean disabled;
    private String email;
    private String name;
    private String password;

    public DisabledUser() {}

    public String getCareer() {        return career;    }
    public void setCareer(String career) {        this.career = career;    }
    public String getCicle() {        return cicle;    }
    public void setCicle(String cicle) {        this.cicle = cicle;    }
    public String getCode() {        return code;    }
    public void setCode(String code) {        this.code = code;    }
    public boolean isDisable() {        return disabled;    }
    public void setDisable(boolean disable) {        this.disabled = disable;    }
    public String getEmail() {        return email;    }
    public void setEmail(String email) {        this.email = email;    }
    public String getName() {        return name;    }
    public void setName(String name) {        this.name = name;    }
    public String getPassword() {        return password;    }
    public void setPassword(String password) {        this.password = password;    }
}

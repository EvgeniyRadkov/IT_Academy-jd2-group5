package com.gmail.radzkovevgeni.app.repository.model;

public class UserInformation {
    private Integer userId;
    private String address;
    private String telephone;
    private Users users;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "UserInformation{" +
                "userId=" + userId +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                ", users=" + users +
                '}';
    }
}

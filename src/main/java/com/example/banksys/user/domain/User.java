package com.example.banksys.user.domain;
import java.util.List;
import java.util.Objects;


/**
 * user entity
 */
public class User {

    private Long userID;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String  email;
    private String phone;

    private boolean enable = true;

    private PrimaryAccount primaryAccount;
    private SavingAccount savingAccount;
    private List<Appointment> appointmentList;
    private List<Recipient> recipientList;


    public User() {
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public PrimaryAccount getPrimaryAccount() {
        return primaryAccount;
    }

    public void setPrimaryAccount(PrimaryAccount primaryAccount) {
        this.primaryAccount = primaryAccount;
    }

    public SavingAccount getSavingAccount() {
        return savingAccount;
    }

    public void setSavingAccount(SavingAccount savingAccount) {
        this.savingAccount = savingAccount;
    }

    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    public List<Recipient> getRecipientList() {
        return recipientList;
    }

    public void setRecipientList(List<Recipient> recipientList) {
        this.recipientList = recipientList;
    }


    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", enable=" + enable +
                ", primaryAccount=" + primaryAccount +
                ", savingAccount=" + savingAccount +
                ", appointmentList=" + appointmentList +
                ", recipientList=" + recipientList +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return enable == user.enable &&
                Objects.equals(userID, user.userID) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(firstname, user.firstname) &&
                Objects.equals(lastname, user.lastname) &&
                Objects.equals(email, user.email) &&
                Objects.equals(phone, user.phone) &&
                Objects.equals(primaryAccount, user.primaryAccount) &&
                Objects.equals(savingAccount, user.savingAccount) &&
                Objects.equals(appointmentList, user.appointmentList) &&
                Objects.equals(recipientList, user.recipientList);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userID, username, password, firstname, lastname, email, phone, enable, primaryAccount, savingAccount, appointmentList, recipientList);
    }
}

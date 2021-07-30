/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;

/**
 *
 * @author ADMIN
 */
public class Registration_DTO implements Serializable, Comparable<Registration_DTO> {

    private String registrationID;
    private String fullName;
    private Integer age;
    private Boolean gender;
    private String email;
    private String phone;
    private String address;
    private Integer numberOfMember;
    private Integer numberOfChildren;
    private Integer numberOfAdults;

    public Registration_DTO(String registrationID, String fullName, Integer age, Boolean gender, String email, String phone, String address, Integer numberOfMember, Integer numberOfChildren, Integer numberOfAdults) {
        this.registrationID = registrationID;
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.numberOfMember = numberOfMember;
        this.numberOfChildren = numberOfChildren;
        this.numberOfAdults = numberOfAdults;
    }

    public String getRegistrationID() {
        return registrationID;
    }

    public void setRegistrationID(String registrationID) {
        this.registrationID = registrationID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getNumberOfMember() {
        return numberOfMember;
    }

    public void setNumberOfMember(Integer numberOfMember) {
        this.numberOfMember = numberOfMember;
    }

    public Integer getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(Integer numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public Integer getNumberOfAdults() {
        return numberOfAdults;
    }

    public void setNumberOfAdults(Integer numberOfAdults) {
        this.numberOfAdults = numberOfAdults;
    }

    @Override
    public String toString() {
        return registrationID + "; " + fullName + "; " + age + "; " + gender + "; " + email + "; "
                + phone + "; " + address + "; " + numberOfMember + "; " + numberOfChildren + "; " + numberOfChildren + "\n";
    }

    @Override
    public int compareTo(Registration_DTO o) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        if (this.fullName.compareTo(o.fullName) > 0) {
            return 1;
        } else if (this.fullName.compareTo(o.fullName) < 0) {
            return -1;
        } else {
            return 0;
        }

    }
}

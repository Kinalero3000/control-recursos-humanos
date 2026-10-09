package main.java.com.jgunzalesindustries.control.recursos.humanos.model;

public class Applicant {
    private String idApplicant;
    private String namesApplicant;
    private String lastname;
    private String email;
    private String phone;
    private String adressId;
    private int age;

    public Applicant(String applicant, String namesApplicant, String lastName, String email, String phone, String addresId, int age) {
        this.idApplicant = applicant;
        this.namesApplicant = namesApplicant;
        this.lastname = lastName;
        this.email = email;
        this.phone = phone;
        this.adressId = addresId;
        this.age = age;
    }

    public String getApplicant() {
        return idApplicant;
    }

    public void setApplicant(String applicant) {
        this.idApplicant = applicant;
    }

    public String getNamesApplicant() {
        return namesApplicant;
    }

    public void setNamesApplicant(String namesApplicant) {
        this.namesApplicant = namesApplicant;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lastName) {
        this.lastname = lastName;
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

    public String getAddresId() {
        return adressId;
    }

    public void setAddresId(String addresId) {
        this.adressId = addresId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    
}

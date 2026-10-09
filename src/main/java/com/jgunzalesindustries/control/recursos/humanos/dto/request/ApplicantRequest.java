package main.java.com.jgunzalesindustries.control.recursos.humanos.dto.request;

public class ApplicantRequest {
    private String namesApplicant;
    private String lastName;
    private String email;
    private String phone;
    private String adressId;
    private int age;

    public ApplicantRequest(String namesApplicant, String lastName, String email, String phone, String adressId, int age) {
        this.namesApplicant = namesApplicant;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.adressId = adressId;
        this.age = age;
    }
    
    public ApplicantRequest(){
    }

    public String getNamesApplicant() {
        return namesApplicant;
    }

    public void setNamesApplicant(String namesApplicant) {
        this.namesApplicant = namesApplicant;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getAdressId() {
        return adressId;
    }

    public void setAdressId(String adressId) {
        this.adressId = adressId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    
}

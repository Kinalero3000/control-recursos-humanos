package main.java.com.jgunzalesindustries.control.recursos.humanos.dto.response;

public class ApplicantResponse {
    private String idApplicant;
    private String namesApplicant;
    private String lastname;
    private String email;
    private String phone;
    private String adressId;
    private int age;

    public ApplicantResponse(String idApplicant, String namesApplicant, String lastname, String email, String phone, String adressId, int age) {
        this.idApplicant = idApplicant;
        this.namesApplicant = namesApplicant;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.adressId = adressId;
        this.age = age;
    }

    public String getIdApplicant() {
        return idApplicant;
    }

    public void setIdApplicant(String idApplicant) {
        this.idApplicant = idApplicant;
    }

    public String getNamesApplicant() {
        return namesApplicant;
    }

    public void setNamesApplicant(String namesApplicant) {
        this.namesApplicant = namesApplicant;
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

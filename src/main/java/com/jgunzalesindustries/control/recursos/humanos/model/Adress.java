package main.java.com.jgunzalesindustries.control.recursos.humanos.model;

public class Adress {
    private String adressId;
    private String city;
    private String town;
    private String zone;
    private String houseAdress;

    public Adress(String adressId, String city, String town, String zone, String houseAdress) {
        this.adressId = adressId;
        this.city = city;
        this.town = town;
        this.zone = zone;
        this.houseAdress = houseAdress;
    }

    public String getAdressId() {
        return adressId;
    }

    public void setAdressId(String adressId) {
        this.adressId = adressId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getHouseAdress() {
        return houseAdress;
    }

    public void setHouseAdress(String houseAdress) {
        this.houseAdress = houseAdress;
    }
    
    
}

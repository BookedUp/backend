package rs.ac.uns.ftn.asd.BookedUp.domain;

public class Address {
    private String country;
    private String city;
    private Integer postalCode;
    private String streetAndNumber;

    public Address() {
    }

    public Address(String country, String city, Integer postalCode, String streetAndNumber) {
        this.country = country;
        this.city = city;
        this.postalCode = postalCode;
        this.streetAndNumber = streetAndNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreetAndNumber() {
        return streetAndNumber;
    }

    public void setStreetAndNumber(String streetAndNumber) {
        this.streetAndNumber = streetAndNumber;
    }

    // Function to copy values from another address
    public void copyValues(Address address) {
        this.country = address.getCountry();
        this.city = address.getCity();
        this.postalCode = address.getPostalCode();
        this.streetAndNumber = address.getStreetAndNumber();
    }
}

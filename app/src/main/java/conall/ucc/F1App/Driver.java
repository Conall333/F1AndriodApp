package conall.ucc.F1App;


import java.io.Serializable;

public class Driver implements Serializable {

    private String name;
    private String team;
    private String races;
    private String image;
    private String image2;
    private String country;
    private String url;

    public Driver(String name, String team, String races, String image, String image2, String country, String url) {
        this.name = name;
        this.team = team;
        this.races = races;
        this.image = image;
        this.image2 = image2;
        this.country = country;
        this.url = url;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String phone) {
        this.team = phone;
    }

    public String getRaces() {
        return races;
    }

    public void setRaces(String races) {
        this.races = races;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.url = country;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}

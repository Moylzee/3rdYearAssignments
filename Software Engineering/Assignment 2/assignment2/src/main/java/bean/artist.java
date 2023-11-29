package bean;

public class artist {
    private String fName;
    private String sName;
    private String nationality;
    private String bYear;
    private String dYear;
    private String bio;
    private String pURL;

    public artist(String fName, String sName, String nationality, String bYear, String dYear, String bio, String pURL){
        setfName(fName);
        setsName(sName);
        setNationality(nationality);
        setbYear(bYear);
        setdYear(dYear);
        setBio(bio);
        setpURL(pURL);
    }
    
    // Getter methods
    public String getfName() {
        return fName;
    }

    public String getsName() {
        return sName;
    }

    public String getNationality() {
        return nationality;
    }

    public String getbYear() {
        return bYear;
    }

    public String getdYear() {
        return dYear;
    }

    public String getBio() {
        return bio;
    }

    public String getpURL() {
        return pURL;
    }

    // Setter methods
    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setbYear(String bYear) {
        this.bYear = bYear;
    }

    public void setdYear(String dYear) {
        this.dYear = dYear;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setpURL(String pURL) {
        this.pURL = pURL;
    }
}
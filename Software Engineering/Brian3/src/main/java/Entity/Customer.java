package Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
// Entity Class
@Entity
//Table to reference
@Table(name = "Customer")
public class Customer {
// Columns to assign to
    @Id   
    @Column(name = "cNum")
    private int cNum;
    @Column(name = "cName")
    private String cName; 
    @Column(name = "pNum")
    private String pNum;
    @Column(name = "email")
    private String email;
    @Column(name = "country")
    private String country;
    @Column(name = "pCode")
    private String pCode;
    @Column(name = "cLimit")
    private double cLimit;
    // Empty Constructor
    public Customer() {}
    // Constructor that takes in parameters
    public Customer(int cNum, String cName, String pNum, String email, String country, String pCode, double cLimit) {
        this.cNum = cNum;
        this.cName = cName;
        this.pNum = pNum;
        this.email = email;
        this.country = country;
        this.pCode = pCode;
        this.cLimit = cLimit;
    }
    // Setters    
    public void setcNum(int cNum) {this.cNum = cNum;}
    public void setcName(String cName) {this.cName = cName;}
    public void setpNum(String pNum) {this.pNum = pNum;}
    public void setEmail(String email) {this.email = email;}
    public void setCountry(String country) {this.country = country;}
    public void setpCode(String pCode) {this.pCode = pCode;}
    public void setcLimit(double cLimit) {this.cLimit = cLimit;}
    // Getters  
    public int getcNum() {return cNum;}
    public String getcName() {return cName;}
    public String getpNum() {return pNum;}
    public String getEmail() {return email;}
    public String getCountry() {return country;}
    public String getpCode() {return pCode;}
    public double getcLimit() {return cLimit;}
}
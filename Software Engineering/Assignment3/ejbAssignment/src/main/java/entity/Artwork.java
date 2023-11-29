package entity;
import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

/**
 *
 * @author Brian Moyles
 */
@Entity
@Table(name = "artwork")
@NamedQueries(
{
    @NamedQuery(name = "Artwork.findAll", query = "SELECT a FROM Artwork a")
})
public class Artwork implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "artworkid")
    private Integer artworkid;
    
    @Column(name = "year")
    private Short year;
    
    @Size(max = 255)
    @Column(name = "title")
    private String title;
    
    @Size(max = 255)
    @Column(name = "coverimage")
    private String coverimage;
      
    @JoinColumn(name = "artistid", referencedColumnName = "artistid")
    @ManyToOne
    private Artist artistid;

    public Artwork(){}

    public Artwork(Integer artworkid)
    {
        this.artworkid = artworkid;
    }

    public Artwork(Integer artworkid, Short year, String title, String coverimage, Artist artistid)
    {
        this.artworkid = artworkid;
        this.year = year;
        this.title = title;
        this.coverimage = coverimage;
        this.artistid = artistid;
    }

    // Getters
    public Integer getArtworkid(){return artworkid;}
    public Short getYear(){return year;}
    public String getTitle(){return title;}
    public Artist getArtist(){return artistid;}
    public String getCoverimage(){return coverimage;}

    // Setters
    public void setYear(Short year){this.year = year;}
    public void setTitle(String title){this.title = title;}
    public void setAuthor(Artist artistid){this.artistid = artistid;}
    public void setArtworkid(Integer artworkid){this.artworkid = artworkid;}
    public void setCoverimage(String coverimage){this.coverimage = coverimage;} 
}
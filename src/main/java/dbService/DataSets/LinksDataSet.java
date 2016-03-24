package dbService.DataSets;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="links")
public class LinksDataSet implements Serializable {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="name", updatable = true)
    private String name;

    @Column(name = "URL", updatable = true)
    private String URL;

    @Column(name = "owner", updatable = false)
    private String owner;

    @SuppressWarnings("UnusedDeclaration")
    public LinksDataSet(){}

    @SuppressWarnings("UnusedDeclaration")
    public LinksDataSet(String name, String URL, String owner){

        this.setName(name);
        this.setURL(URL);
        this.setOwner(owner);
    }

    public long getId() {
        return id;
    }


    @SuppressWarnings("UnusedDeclaration")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @SuppressWarnings("UnusedDeclaration")
    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    @SuppressWarnings("UnusedDeclaration")
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String toString(){
        return "LinksDataSet{" + "id=" + id + ", name='" + name + "\'" + ", URL=" + URL + "}\n";
    }
}

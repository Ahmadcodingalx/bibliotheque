package org.example.livres;


import java.sql.Date;

public class BookData {

    private Integer ID;
    private String titre;
    private String auteur;
    private String genre;
    private Date dateDePublication;
    private Double prix;
    private String image;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Date getDateDePublication() {
        return dateDePublication;
    }

    public void setDateDePublication(Date dateDePublication) {
        this.dateDePublication = dateDePublication;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public BookData(Integer ID, String titre, String auteur, String genre, Date dateDePublication, Double prix, String image) {
        this.ID = ID;
        this.titre = titre;
        this.auteur = auteur;
        this.genre = genre;
        this.dateDePublication = dateDePublication;
        this.prix = prix;
        this.image = image;
    }
}

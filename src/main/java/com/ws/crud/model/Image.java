package com.ws.crud.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "image")
public class Image {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "filename")
    private String filename;
    @Column(name = "enchere_id")
    private int enchere_id;
    public Image() {
        super();
    }
    public Image(String filename, int enchere_id) {
        super();
        this.filename = filename;
        this.enchere_id = enchere_id;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getFilename() {
        return filename;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }
    public int getEnchere_id() {
        return enchere_id;
    }
    public void setEnchere_id(int enchere_id) {
        this.enchere_id = enchere_id;
    }
    
    
}

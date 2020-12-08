package main.java.com.nagarro.entity;

import javax.persistence.*;

import java.io.File;
import java.util.Arrays;

@Entity
@Table(name = "images", schema = "demo")
public class ImagesEntity {
    private Integer id;
    private byte[] image;
    private String name;
    private Integer size;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    @Transient
    File file;
    
    public File getFile() {
    	return file;
    }
    
    @ManyToOne
    UsersEntity user;
    
    @Basic
    @Column(name = "image", nullable = false)
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 200)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "size", nullable = false)
    public Integer getSize() {
        return size;
    }
    
    public UsersEntity getUser() {
    	return user;
    }
    
    public void setUser(UsersEntity user) {
    	this.user = user;
    }

    public void setImg(File file) {
    	this.file = file;
    	this.size = (int) file.length();
    	this.name = file.getName();
    }
    
    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImagesEntity that = (ImagesEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (!Arrays.equals(image, that.image)) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (size != null ? !size.equals(that.size) : that.size != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + Arrays.hashCode(image);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (size != null ? size.hashCode() : 0);
        return result;
    }
}

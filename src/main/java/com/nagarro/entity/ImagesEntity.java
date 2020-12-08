package main.java.com.nagarro.entity;

import javax.persistence.*;

import java.io.File;
import java.util.Arrays;

@Entity
@Table(name = "images")
public class ImagesEntity {
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue
    private Integer imageId;
	
	@Column(name = "image", nullable = false)
    private String image;
	
	@Column(name = "name", nullable = false, length = 200)
    private String name;
	
	@Column(name = "size", nullable = false)
    private Integer size;

    @Transient
    File file;
    
    @ManyToOne
    @JoinColumn(name="userId")
    UsersEntity user;
    
     
    public Integer getImageId() {
        return imageId;
    }

    public File getFile() {
    	return file;
    }
    
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

     
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

        if (imageId != null ? !imageId.equals(that.imageId) : that.imageId != null) return false;
        if (image != null ? !image.equals(that.image) : that.image != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (size != null ? !size.equals(that.size) : that.size != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = imageId != null ? imageId.hashCode() : 0;
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (size != null ? size.hashCode() : 0);
        return result;
    }
}

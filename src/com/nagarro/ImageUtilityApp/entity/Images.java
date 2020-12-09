package com.nagarro.ImageUtilityApp.entity;

import java.io.File;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "images")
public class Images {

	@Id
	@GeneratedValue
	private Integer imageId;

	//@Column(name = "image", nullable = false)
	private String imagePath;

	//@Column(name = "name", nullable = false, length = 200)
	private String name;

	//@Column(name = "size", nullable = false)
	private Integer size;

//	@Transient
//	File file;

	@ManyToOne
	@JoinColumn(name="userId")
	Users userId;

	public Integer getId() {
		return imageId;
	}

//	public File getFile() {
//		return file;
//	}

	public String getImage() {
		return imagePath;
	}

	public void setImage(String image) {
		this.imagePath = image;
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

	public Users getUserId() {
		return userId;
	}

	public void setUserId(Users user) {
		this.userId = user;
	}

	public void setImg(File file) {
		// this.file = file;
		this.size = (int) file.length();
		this.name = file.getName();
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Images that = (Images) o;

		if (imageId != null ? !imageId.equals(that.imageId) : that.imageId != null)
			return false;
		if (imagePath != null ? !imagePath.equals(that.imagePath) : that.imagePath != null)
			return false;
		if (name != null ? !name.equals(that.name) : that.name != null)
			return false;
		if (size != null ? !size.equals(that.size) : that.size != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = imageId != null ? imageId.hashCode() : 0;
		result = 31 * result + (imagePath != null ? imagePath.hashCode() : 0);
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (size != null ? size.hashCode() : 0);
		return result;
	}
}

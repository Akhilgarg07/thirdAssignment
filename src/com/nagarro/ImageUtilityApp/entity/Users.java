package com.nagarro.ImageUtilityApp.entity;

import javax.persistence.*;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.query.Query;

import com.nagarro.ImageUtilityApp.util.HibernateUtil;

import java.util.List;

@Entity
@Table(name = "users")
@DynamicUpdate
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;

	//@Column(name = "first_name", nullable = true, length = 20)
	private String firstName;

	//@Column(name = "last_name", nullable = true, length = 20)
	private String lastName;

	//@Column(name = "username", nullable = true, length = 250)
	private String username;
	
	//@Column(name = "password", nullable = true, length = 250)
	private String password;

	@OneToMany(mappedBy = "userId")
	List<Images> imageList;

	public Integer getId() {
		return userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Basic

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Images> getImageList() {
		return imageList;
	}

	public void setImageList(List<Images> imageList) {
		this.imageList = imageList;
	}
	
	public static List<Images> getImageList(String username){
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Users where username=:param");
		query.setParameter("param", username);
		Users user = (Users) query.uniqueResult();
		List<Images> li;
		try {
			li =  user.getImageList();
		}catch(Exception e) {
			li = null;
		}
		session.getTransaction().commit();
		session.close();
		return li;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Users that = (Users) o;

		if (userId != null ? !userId.equals(that.userId) : that.userId != null)
			return false;
		if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null)
			return false;
		if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null)
			return false;
		if (username != null ? !username.equals(that.username) : that.username != null)
			return false;
		if (password != null ? !password.equals(that.password) : that.password != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = userId != null ? userId.hashCode() : 0;
		result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
		result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
		result = 31 * result + (username != null ? username.hashCode() : 0);
		result = 31 * result + (password != null ? password.hashCode() : 0);
		return result;
	}
}

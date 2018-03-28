package se.arctisys.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import se.arctisys.constraints.FieldMatch;

/**
 * Created by tornqvist on 2018-03-23.
 */
@Entity
@Table(name = "user")
@FieldMatch.List({
    @FieldMatch(first = "password", second = "confirmPassword", message = "Lösenorden måste vara lika"),
    //@FieldMatch(first = "email", second = "confirmEmail", message = "The email fields must match")
})
public class User {

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	
	@Column(name = "email")
	@Email(message = "*Ange en korrekt epost-adress")
	@NotEmpty(message = "*Ange en epost-adress")
	private String email;
	
	@Column(name = "password")
	@Length(min = 8, message = "*Ditt lösenord måste vara minst 8 tecken")
	@NotEmpty(message = "*Ange ett lösenord")
	private String password;

	@Column(name = "confirm_password")
	@Length(min = 8, message = "*Ditt lösenord måste vara minst 8 tecken")
	@NotEmpty(message = "*Bekräfta ditt lösenord")
	private String confirmPassword;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "last_name")
	@NotEmpty(message = "*Ange ditt efternamn")
	private String lastName;

	@Column(name = "first_name")
	@NotEmpty(message = "*Ange ditt förnamn")
	private String firstName;

	@Column(name = "active")
	private int active;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"))
	private Set<Role> roles;

	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="user")
	private Account account;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="user")
	@OrderBy("creationDate")	
	private Set<UserShare> userShares = new HashSet<UserShare>();	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public Set<UserShare> getUserShares() {
		return userShares;
	}

	public void setUserShares(Set<UserShare> userShares) {
		this.userShares = userShares;
	}
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}	

	@Transient
	public String getName() {
		return firstName + " " + lastName;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}

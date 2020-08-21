package br.com.caelum.casadocodigoapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Author {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
    private String email;
    private String avatarUrl;
    
    @Lob
    private String description;
	
    
    /**
     * @deprecated hibernate only
     */
    public Author() {
	}
    
	public Author(String name, String email, String avatarUrl, String description) {
		super();
		this.name = name;
		this.email = email;
		this.avatarUrl = avatarUrl;
		this.description = description;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAvatarUrl() {
		return avatarUrl;
	}
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Author [id=" + id + ", name=" + name + ", email=" + email + ", avatarUrl=" + avatarUrl
				+ ", description=" + description + "]";
	}
	
	
}

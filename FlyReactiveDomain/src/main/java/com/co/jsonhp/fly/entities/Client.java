package com.co.jsonhp.fly.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "client")
@NamedQueries({
	@NamedQuery(name = "client.findAll", query= "SELECT c FROM Client c"),
	@NamedQuery(name = "client.findByIdentification", query= "SELECT c FROM Client c WHERE c.identification = :identification")
})
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id, identification;
	
	private String name, lastname;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getIdentification() {
		return this.identification;
	}
	
	public void setIdentification(int identification) {
		this.identification = identification;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
}

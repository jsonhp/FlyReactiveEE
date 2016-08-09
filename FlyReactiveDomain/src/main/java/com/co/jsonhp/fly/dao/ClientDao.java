package com.co.jsonhp.fly.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.collections.EnumerationUtils;

import com.co.jsonhp.fly.entities.Client;

import rx.Observable;

public class ClientDao {

	String queryFindByIdentification = "client.findByIdentification";
	
	@PersistenceContext
	EntityManager entityManager;


	public Observable<Client> getClientByIdentification(int identification) {
		System.out.println(identification);
		System.out.println(entityManager);
		TypedQuery<Client> query = entityManager.createNamedQuery(queryFindByIdentification, Client.class);
		System.out.println(query);
		query.setParameter("identification", identification);
		return Observable.just(query.getResultList().get(0));
	}
	
}

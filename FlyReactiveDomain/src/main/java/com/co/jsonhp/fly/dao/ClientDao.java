package com.co.jsonhp.fly.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.co.jsonhp.fly.cdi.TransactionalIT;
import com.co.jsonhp.fly.entities.Client;
import com.co.jsonhp.fly.exceptions.ClientNotFoundException;

import rx.Observable;

public class ClientDao {

	String queryFindByIdentification = "client.findByIdentification";
	
	@PersistenceContext
	EntityManager entityManager;
	
	List<Client> clients;
	
	@TransactionalIT
	public Observable<Client> getClientByIdentification(int identification) throws ClientNotFoundException  {
		return Observable.fromCallable(() -> getClientByIdentificationImp(identification));
		
	}
	
	@TransactionalIT
	public Client getClientByIdentificationImp(int identification) throws ClientNotFoundException {
		List<Client> list = entityManager.createNamedQuery(queryFindByIdentification, Client.class)
				.setParameter("identification", identification)
				.getResultList();
		
		if (list.isEmpty()) {
			throw new ClientNotFoundException("Cliente no encontrado");
		}
		
		return list.get(0);		
	}		
}

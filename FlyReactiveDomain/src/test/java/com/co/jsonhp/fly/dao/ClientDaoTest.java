package com.co.jsonhp.fly.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.co.jsonhp.fly.entities.Client;
import com.co.jsonhp.fly.exceptions.ClientNotFoundException;

import rx.Observable;

@RunWith(MockitoJUnitRunner.class)
public class ClientDaoTest {

	Client receivedClient = new Client();
	String queryFindByIdentification = "client.findByIdentification";
	Client client = new Client();
	
	@InjectMocks
	ClientDao clientDao;
	
	@Mock
	EntityManager entityManager;
	
	@Mock
	TypedQuery<Client> query;
	
	@Mock
	private List<Client> resultList;

	@Before
	public void setUp() {
		client = new Client();
		client.setId(1);
		client.setIdentification(1075274577);
		client.setName("JSon");
		client.setLastname("HP");
	}
	
	@Test
	public void mustObtainClientByIdentificaction() throws ClientNotFoundException {
		//Arrange
		int identificationClient = 1075274577;
		Mockito.when(entityManager.createNamedQuery(queryFindByIdentification, Client.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(resultList);
		Mockito.when(query.getResultList().get(0)).thenReturn(client);
		
		//Act
		Observable<Client> oservableClient = clientDao.getClientByIdentification(identificationClient);
		oservableClient.subscribe(c -> receivedClient = c);
		
		//Assert
		assertEquals(client,receivedClient);
		Mockito.verify(query).setParameter("identification", identificationClient);
	}
	
	@Test(expected = ClientNotFoundException.class)
	public void mustThrowIndexException() throws ClientNotFoundException {
		//Arrange
		int identificationClient = 1075274578;
		List<Client> listClients = new ArrayList<Client>();
		Mockito.when(entityManager.createNamedQuery(queryFindByIdentification, Client.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(listClients);
		//Mockito.when(query.getResultList().get(0)).thenReturn(client);
		
		//Act
		clientDao.getClientByIdentification(identificationClient);
		
		//Assert
		Mockito.verify(query).setParameter("identification", identificationClient);
		
		
	}
	
}

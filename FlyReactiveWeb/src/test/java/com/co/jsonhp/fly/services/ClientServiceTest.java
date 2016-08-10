package com.co.jsonhp.fly.services;

import javax.ws.rs.container.AsyncResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.co.jsonhp.fly.dao.ClientDao;
import com.co.jsonhp.fly.entities.Client;
import com.co.jsonhp.fly.exceptions.ClientNotFoundException;

import rx.Observable;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {

	@InjectMocks
	ClientService clientService;
	
	@Mock
	ClientDao clientDao;
	
	@Mock
	AsyncResponse asyncResponse;
	
	@Test
	public void mustObtainClientByIdentification() throws ClientNotFoundException {
		//Arrange
		int identification = 1234;
		Client clientExpected = new Client();
		Observable<Client> clientObservable = Observable.just(clientExpected);
		Mockito.when(clientDao.getClientByIdentification(identification)).thenReturn(clientObservable);
		
		//Act
		clientService.getClientByIdentification(identification, asyncResponse);		
		
		//Assert
		Mockito.verify(clientDao).getClientByIdentification(identification);
		Mockito.verify(asyncResponse).resume(clientExpected);
		
		
	}
	
}

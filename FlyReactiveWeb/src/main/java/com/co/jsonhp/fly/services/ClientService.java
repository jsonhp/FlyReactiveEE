package com.co.jsonhp.fly.services;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;

import com.co.jsonhp.fly.dao.ClientDao;

import rx.schedulers.Schedulers;

@Path("/client")
public class ClientService {
	
	//@Inject
	ClientDao clientDao = new ClientDao();

	@GET
	@Path("/getByIdentification/{identification}")
	public void getClientByIdentification(@PathParam("identification") int identification, @Suspended final AsyncResponse asyncResponse) {
		clientDao.getClientByIdentification(identification)
			.subscribeOn(Schedulers.computation())
			.doOnError(throwable -> asyncResponse.resume("Error: " + throwable.getMessage()))
			.subscribe(client -> asyncResponse.resume(client));
	}
}

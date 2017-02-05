package org.suri.myWebservice.mobileApp;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

@Path("/person")
public class PersonService implements ContainerResponseFilter {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Person> getPeople() {
		PersonDao pd = new PersonDao();
		return pd.getPeople();
	}

	@GET
	@Path("{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Person getPerson(@PathParam("name") String name) {
		return new PersonDao().getPerson(name);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String addPerson(Person person) {
	return	new PersonDao().addPerson(person)?"success":"failed";
		
	}
	
	

	@Override
	public void filter(ContainerRequestContext request, ContainerResponseContext response) throws IOException {
		MultivaluedMap<String, Object> headers = response.getHeaders();
		headers.add("Access-Control-Allow-Origin", "*");
		headers.add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");			
		headers.add("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, X-Codingpedia");
		
	}

}

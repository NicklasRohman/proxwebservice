package service;

import java.util.*;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import dto.EventDto;
import ejb.EventEJB;
import ejb.ProfileEJB;
import entitys.EventEntity;

@RequestScoped
@Path("events")
public class EventService {

	@Inject
	private Logger log;

	@EJB
	private ProfileEJB profileEJB;
	@EJB
	private EventEJB eventEJB;

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<EventDto> getEvents() {
		List<EventDto> result = new ArrayList<EventDto>();
		for (EventEntity event : eventEJB.findAll()) {
			result.add(entToDTO(event));
		}
		log.info("Return {} events", result.size());
		eventEJB = null;
		return result;
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_XML)
	public List<EventDto> getEvent(@PathParam("id") int id) {
		List<EventDto> result = new ArrayList<EventDto>();
		for (EventEntity event : eventEJB.findAll()) {
			if (event.getEventid() == id) {
				result.add(entToDTO(event));
			}
		}
		log.info("Return {} event", result.size());
		eventEJB = null;
		return result;
	}

	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public void createNewEvent(EventDto[] eventDto) {
		EventEntity entity = new EventEntity();

		for (EventDto dto : eventDto) {		
		entity.setCaregory(dto.getCatagory());
		entity.setName(dto.getName());
		entity.setDate(dto.getDate());
		entity.setLocation(dto.getLocation());
		entity.setRaiting(dto.getRaiting());
		}
		eventEJB.merge(entity);
	}

	@PUT
	@Path("{id}")
	@Produces(MediaType.APPLICATION_XML)
	public void editEvent(@PathParam("id") int id, EventDto[] eventDto) {
		EventEntity entity = new EventEntity();
		entity.setEventid(id);

		for (EventDto dto : eventDto) {
			if (entity.getEventid() == id) {
				entity.setCaregory(dto.getCatagory());
				entity.setName(dto.getName());
				entity.setDate(dto.getDate());
				entity.setLocation(dto.getLocation());
				entity.setRaiting(dto.getRaiting());
			}
		}
		eventEJB.merge(entity);
	}

	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_XML)
	public void deleteEvent(@PathParam("id") int id) {
		eventEJB.delete(id);
	}

	private EventDto entToDTO(EventEntity event) {
		EventDto result = new EventDto(event.getEventid(), event.getName(), event.getCaregory(), event.getDate(),
				event.getLocation(), event.getRaiting());
		return result;
	}
}
//http://localhost:8080/ProjectXWebservice/events
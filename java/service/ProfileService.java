package service;

import java.util.*;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import dto.ProfileDto;
import ejb.EventEJB;
import ejb.ProfileEJB;
import entitys.ProfileEntity;

@RequestScoped
@Path("profiles")
public class ProfileService {

	@Inject
	private Logger log;

	@EJB
	private ProfileEJB profileEJB;
	@EJB
	private EventEJB eventEJB;

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<ProfileDto> getProfiles() {
		List<ProfileDto> result = new ArrayList<ProfileDto>();
		for (ProfileEntity profile : profileEJB.findAll()) {

			result.add(entToDTO(profile));
		}
		log.info("Return {} profiles", result.size());
		profileEJB = null;
		return result;
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_XML)
	public List<ProfileDto> getProfile(@PathParam("id") String id) {
		List<ProfileDto> result = new ArrayList<ProfileDto>();
		for (ProfileEntity profile : profileEJB.findAll()) {
			if (profile.getEmails().equalsIgnoreCase(id)) {
				result.add(entToDTO(profile));
			}
		}
		log.info("Return {} profile", result.size());
		profileEJB = null;
		return result;
	}

	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public ProfileDto createNewProfile(ProfileDto dto) {
		ProfileEntity entity = new ProfileEntity(dto);
		for (ProfileEntity profile : profileEJB.findAll()) {
			if (!profile.getEmails().equalsIgnoreCase(dto.getEmails())) {
				entity = profileEJB.merge(entity); 		
			}
		}
		
		return entToDTO(entity);
		
	}

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public void editProfile(@PathParam("id") String id, ProfileDto[] profileDto) {
		ProfileEntity entity = new ProfileEntity();
		entity.setEmails(id);

		for (ProfileDto dto : profileDto) {
			if (entity.getEmails().equalsIgnoreCase(id)) {
				entity.setPassword(dto.getPassword());
				entity.setName(dto.getName());
				entity.setBio(dto.getBio());
				entity.setProfileRating(dto.getProfileRating());
			}
		}
		profileEJB.merge(entity);
	}

	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_XML)
	public void deleteProfile(@PathParam("id") String id) {
		profileEJB.delete(id);
	}

	private ProfileDto entToDTO(ProfileEntity ent) {
		ProfileDto result = new ProfileDto(ent.getEmails(), ent.getPassword(), ent.getName(), ent.getBio(),
				ent.getProfileRating());

		return result;
	}
}
//http://localhost:8080/ProjectXWebservice/profiles
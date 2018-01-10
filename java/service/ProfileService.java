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
	public List<ProfileDto> getProfile(@PathParam("id") int id) {
		List<ProfileDto> result = new ArrayList<ProfileDto>();
		for (ProfileEntity profile : profileEJB.findAll()) {
			if (profile.getId()==id) {
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
	public void createNewProfile(ProfileDto[] profileDto) {
		ProfileEntity entity = new ProfileEntity();

		for (ProfileDto dto : profileDto) {
			entity.setEmail(dto.getEmail());
			entity.setPassword(dto.getPassword());
			entity.setName(dto.getName());
			entity.setBio(dto.getBio());
			entity.setProfileRating(dto.getProfileRating());
		}
		profileEJB.merge(entity);
	}

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public void editProfile(@PathParam("id") int id, ProfileDto[] profileDto) {
		ProfileEntity entity = new ProfileEntity();
		entity.setId(id);

		for (ProfileDto dto : profileDto) {
			if (entity.getId() == id) {

				entity.setEmail(dto.getEmail());
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
	public void deleteProfile(@PathParam("id") int id) {
		profileEJB.delete(id);
	}

	private ProfileDto entToDTO(ProfileEntity ent) {
		ProfileDto result = new ProfileDto(ent.getId(), ent.getEmail(), ent.getPassword(), ent.getName(), ent.getBio(),
				ent.getProfileRating());

		return result;
	}
}
//http://localhost:8080/ProjectXWebservice/profiles
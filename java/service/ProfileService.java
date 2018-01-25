package service;

import java.util.*;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import dto.ProfileDto;
import dto.RoleDto;
import ejb.EventEJB;
import ejb.ProfileEJB;
import ejb.RoleEJB;
import entitys.ProfileEntity;
import entitys.RoleEntity;

@RequestScoped
@Path("profiles")
public class ProfileService {

	@Inject
	private Logger log;

	@EJB
	private ProfileEJB profileEJB;
	@EJB
	private EventEJB eventEJB;
	@EJB
	private RoleEJB roleEJB;

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<ProfileDto> getProfiles() {
		List<ProfileDto> result = new ArrayList<ProfileDto>();
		for (ProfileEntity profile : profileEJB.findAll()) {
			result.add(entToDTO(profile));

		}
		// log.info("Return {} profiles", result.size());
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
		// log.info("Return {} profile", result.size());
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
	public void editProfile(@PathParam("id") String id, ProfileDto dto) {

		ProfileEntity ent = profileEJB.findById(id);
		List<RoleEntity> roles = new ArrayList<>();

		for (RoleDto r : dto.getRoleResult()) {
			roles.add(roleEJB.findById(r.getRolename()));
		}

		ent.setBio(dto.getBio());
		ent.setName(dto.getName());
		ent.setPassword(dto.getPassword());
		ent.setRoles(roles);
		ent.setEventResult(eventEJB.findAll());
		profileEJB.merge(ent);
	}

	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_XML)
	public void deleteProfile(@PathParam("id") String id) {
		profileEJB.delete(id);
	}

	private ProfileDto entToDTO(ProfileEntity ent) {
		List<RoleDto> roles = new ArrayList<>();
		for (RoleEntity r : ent.getRoles()) {
			roles.add(new RoleDto(r.getRolename()));
		}
		ProfileDto result = new ProfileDto(ent.getEmails(), ent.getPassword(), ent.getName(), ent.getBio(), roles);

		return result;
	}
}
// http://localhost:8080/ProjectXWebservice/profiles
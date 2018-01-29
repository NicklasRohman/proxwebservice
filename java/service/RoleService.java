package service;

import java.util.*;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import dto.RoleDto;
import ejb.RoleEJB;
import entitys.RoleEntity;

@RequestScoped
@Path("roleService")
public class RoleService {


	@EJB
	private RoleEJB roleEJB;

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<RoleDto> getRoles() {
		List<RoleDto> result = new ArrayList<RoleDto>();
		for (RoleEntity role : roleEJB.findAll()) {
			result.add(entToDTO(role));
		}
		roleEJB = null;
		return result;
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_XML)
	public List<RoleDto> getRole(@PathParam("id") String id) {
		List<RoleDto> result = new ArrayList<RoleDto>();
		for (RoleEntity role : roleEJB.findAll()) {
			if (role.getRolename().equalsIgnoreCase(id)) {
				result.add(entToDTO(role));
			}
		}
		roleEJB = null;
		return result;
	}

	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public RoleDto createNewProfile(RoleDto roleDto) {
		RoleEntity entity = new RoleEntity();

		entity = roleEJB.merge(entity);
		return entToDTO(entity);
	}

	@PUT
	@Path("{id}")
	@Produces(MediaType.APPLICATION_XML)
	public void editRoles(@PathParam("id") String id, RoleDto[] roleDto) {
		RoleEntity entity = new RoleEntity();
		entity.setRolename(id);

		roleEJB.merge(entity);
	}

	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_XML)
	public void deleteRole(@PathParam("id") String id) {
		roleEJB.delete(id);
	}

	private RoleDto entToDTO(RoleEntity role) {
		RoleDto result = new RoleDto();
		return result;
	}
}
// http://localhost:8080/ProjectXWebservice/roles
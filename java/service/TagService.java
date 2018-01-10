package service;

import java.util.*;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import dto.TagDto;
import ejb.TagEJB;
import entitys.TagEntity;

@RequestScoped
@Path("tags")
public class TagService {

	@Inject
	private Logger log;

	@EJB
	private TagEJB tagEJB;

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<TagDto> getTags() {
		List<TagDto> result = new ArrayList<TagDto>();
		for (TagEntity tag : tagEJB.findAll()) {
			result.add(entToDTO(tag));
		}
		log.info("Return {} tags", result.size());
		tagEJB = null;
		return result;
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_XML)
	public List<TagDto> getTag(@PathParam("id") int id) {
		List<TagDto> result = new ArrayList<TagDto>();
		for (TagEntity tag : tagEJB.findAll()) {
			if (tag.getId() == id) {
				result.add(entToDTO(tag));
			}
		}
		log.info("Return {} tag", result.size());
		tagEJB = null;
		return result;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public void createNewProfile(TagDto[] tagDto) {
		TagEntity entity = new TagEntity();

		for (TagDto dto : tagDto) {
				entity.setName(dto.getName());
			}
		tagEJB.merge(entity);
	}


	@PUT
	@Path("{id}")
	@Produces(MediaType.APPLICATION_XML)
	public void editTag(@PathParam("id") int id, TagDto[] tagDto) {
		TagEntity entity = new TagEntity();
		entity.setId(id);

		for (TagDto dto : tagDto) {
			if (entity.getId() == id) {
				entity.setName(dto.getName());
			}
		}
		tagEJB.merge(entity);
	}

	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_XML)
	public void deleteTag(@PathParam("id") int id) {
		tagEJB.delete(id);
	}

	private TagDto entToDTO(TagEntity tag) {
		TagDto result = new TagDto(tag.getId(), tag.getName());
		return result;
	}
}
//http://localhost:8080/ProjectXWebservice/tags
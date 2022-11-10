package ch.zli.m223.controller;

import java.util.List;
import java.util.Optional;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import ch.zli.m223.model.Status;
import ch.zli.m223.service.StatusService;


@Path("/status")
@Tag(name = "Status", description = "Handling of status")
@RolesAllowed({ "User", "Admin" })
public class StatusController {
  
  @Inject
  StatusService statusService;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Operation(
      summary = "Index all status.", 
      description = "Returns a list of all status."
  )
  public List<Status> index() {
      return statusService.findAll();
  }

  @Path("/{id}")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Operation(
      summary = "Get a single status.", 
      description = "Gets an status by its id."
  )
  public Optional<Status> findById(@PathParam("id") Long id) {
    return statusService.findStatusById(id);
}

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Operation(
      summary = "Creates a new status.", 
      description = "Creates a new status and returns the newly added status."
  )
  @PermitAll
  public Status create(Status status) {
     return statusService.createStatus(status);
  }

  @Path("/{id}")
  @DELETE
  @Operation(
      summary = "Deletes an status.",
      description = "Deletes an status by its id."
  )
  public void delete(@PathParam("id") Long id) {
      statusService.deleteStatus(id);
  }

  @Path("/{id}")
  @PUT
  @Operation(
      summary = "Updates an status.",
      description = "Updates an status by its id."
  )
  public Status update(@PathParam("id") Long id, Status status) {
      return statusService.updateStatus(id, status);
  }
}

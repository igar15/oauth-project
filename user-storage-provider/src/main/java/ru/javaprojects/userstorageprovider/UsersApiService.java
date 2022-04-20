package ru.javaprojects.userstorageprovider;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
public interface UsersApiService {

    @GET
    @Path("/{email}")
    User getUserDetails(@PathParam("email") String email);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{email}/verify-password")
    VerifyPasswordResponse verifyPassword(@PathParam("email") String email, String password);
}

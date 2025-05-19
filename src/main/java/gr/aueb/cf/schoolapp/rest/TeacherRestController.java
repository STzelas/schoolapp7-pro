package gr.aueb.cf.schoolapp.rest;

import gr.aueb.cf.schoolapp.core.exceptions.EntityAlreadyExistsException;
import gr.aueb.cf.schoolapp.core.exceptions.EntityGenericException;
import gr.aueb.cf.schoolapp.core.exceptions.EntityInvalidArgumentException;
import gr.aueb.cf.schoolapp.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapp.dto.TeacherReadOnlyDTO;
import gr.aueb.cf.schoolapp.dto.TeacherUpdateDTO;
import gr.aueb.cf.schoolapp.service.ITeacherService;
import gr.aueb.cf.schoolapp.validator.ValidatorUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import lombok.RequiredArgsConstructor;

import java.net.URI;
import java.util.List;

/**
 * Controller με RestAPI
 */
// Τι instances δημιουργεί εξαρτάται απο τον χαρακτηρισμό
@ApplicationScoped
@RequiredArgsConstructor(onConstructor_ = @__(@Inject))
@Path("/teachers")
public class TeacherRestController {

    private final ITeacherService teacherService;

    // @RequiredArgsConstructor(onConstructor_ = @__(@Inject)) αλλιώς
//    @Inject // dependency injection
//    public TeacherRestController(ITeacherService teacherService) {
//        this.teacherService = teacherService;
//    }

    /**
     * Response επιστρέφουμε τυπικά στην jakarta
     * Container που έχει Http error codes και payload
     *
     * Το @Context κάνει inject απο IOC Container
     * Τι κάνει inject? Contextual info δηλαδή: Security information ή
     * για τον principal πχ ποιος είναι Logged in?
     * ή είναι το UriInfo,
     * Ποιό είναι το URI που έχει κληθεί?
     * Υπάρχει το info μεσα στο UriInfo, κάνουμε inject at runtime
     */
    @POST
    @Path("")
    /**
     * Όταν κουβαλάει data το request θέλουμε @Consumes(MediaType. ότι media είναι πχ json, xml κλπ)
     * Όταν κουβαλάει data το response θέλουμε @Produces(MediaType. ότι media είναι πχ json, xml κλπ)
     */
    @Consumes(MediaType.APPLICATION_JSON) // Όταν υπάρχει data request, στο GET πχ δε χρειάζεται
    @Produces(MediaType.APPLICATION_JSON) // στέλνει payload json, response / Στο GET χρειάζεται σίγουρα
    public Response addTeacher(TeacherInsertDTO insertDTO, @Context UriInfo uriInfo)
            throws EntityInvalidArgumentException, EntityAlreadyExistsException {
        List<String> errors = ValidatorUtil.validateDTO(insertDTO);
        if (!errors.isEmpty()) {
            throw new EntityInvalidArgumentException("Teacher", String.join("\n", errors));
        }

        TeacherReadOnlyDTO readOnlyDTO = teacherService.insertTeacher(insertDTO);

        /**
         * Αυτό που θα επιστρέψουμε μέσα στους headers
         * Είναι συνένωση του absolute path + id ("/teachers/(id) πχ 1")
         */
        URI newResourceUri = uriInfo
                .getAbsolutePathBuilder()
                .path(String.valueOf(readOnlyDTO.id()))
                .build();

        /**
         * Τρία πράγματα περνάμε στα responses
         * 1. στοιχεία του header (αν θέλουμε)
         * 2. HTTP status code,
         * 3. payload
         */
        return Response.created(newResourceUri)
                .entity(readOnlyDTO)
                .build();
    }

    /**
     * Τελικό URI -> /teachers/{το id του teacher πχ 1},
     * ΣΤΟ REST Δεν χρησιμοποιούμε query params για resources !!!
     * Query params, μόνο για page / size / sort κλπ, όχι για πόρους
     * Εδώ έχουμε path params!
     */
    @Path("/{teacherId}")
    public Response updateTeacher(TeacherUpdateDTO updateDTO)
}

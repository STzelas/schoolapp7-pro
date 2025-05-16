package gr.aueb.cf.schoolapp.rest;

import gr.aueb.cf.schoolapp.core.exceptions.EntityGenericException;
import gr.aueb.cf.schoolapp.core.exceptions.EntityInvalidArgumentException;
import gr.aueb.cf.schoolapp.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapp.service.ITeacherService;
import gr.aueb.cf.schoolapp.validator.ValidatorUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import lombok.RequiredArgsConstructor;

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
    public Response addTeacher(TeacherInsertDTO insertDTO, @Context UriInfo uriInfo) throws EntityInvalidArgumentException {
        List<String> errors = ValidatorUtil.validateDTO(insertDTO);
        if (!errors.isEmpty()) {
            throw new EntityInvalidArgumentException("Teacher", String.join("\n", errors));
        }


    }



}

package gr.aueb.cf.schoolapp.service;

import gr.aueb.cf.schoolapp.core.exceptions.AppServerException;
import gr.aueb.cf.schoolapp.core.exceptions.EntityNotFoundException;
import gr.aueb.cf.schoolapp.dao.IUserDAO;
import gr.aueb.cf.schoolapp.dto.UserInsertDTO;
import gr.aueb.cf.schoolapp.dto.UserReadOnlyDTO;
import gr.aueb.cf.schoolapp.mapper.Mapper;
import gr.aueb.cf.schoolapp.model.User;
import gr.aueb.cf.schoolapp.service.util.JPAHelper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@RequiredArgsConstructor ή αυτό (lombok) ή με @Inject όπως κάτω
@ApplicationScoped
public class UserServiceImpl implements IUserService{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final IUserDAO userDAO;

    @Inject
    public UserServiceImpl(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserReadOnlyDTO insertUser(UserInsertDTO dto) throws AppServerException {

        try {
            JPAHelper.beginTransaction();
            User user = Mapper.mapToUser(dto);

            UserReadOnlyDTO readOnlyDTO = userDAO.insert(user)
                            .map(Mapper::mapToUserReadOnlyDTO)
                            .orElseThrow(() -> new AppServerException("User", "User with username: " + dto.getUsername() + " not inserted"));


            JPAHelper.commitTransaction();
            LOGGER.info("User with username= {} inserted", dto.getUsername());
            return readOnlyDTO;
        } catch (AppServerException e) {
            JPAHelper.rollbackTransaction();
            LOGGER.error("User with username = {} not insered", dto.getUsername(), e);
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }
    }

    @Override
    public UserReadOnlyDTO getUserByUsername(String username) throws EntityNotFoundException {
        return null;
    }

    @Override
    public boolean isUserValid(String username, String password) {
        return false;
    }

    @Override
    public boolean isEmailExists(String username) {
        return false;
    }
}

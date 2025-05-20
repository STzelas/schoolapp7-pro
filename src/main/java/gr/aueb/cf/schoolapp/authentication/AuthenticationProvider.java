package gr.aueb.cf.schoolapp.authentication;

import gr.aueb.cf.schoolapp.dto.UserLoginDTO;
import gr.aueb.cf.schoolapp.service.IUserService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor(onConstructor_ = @__(@Inject))
public class AuthenticationProvider {

    /**
     * Επειδή είναι final δημιουργεί
     * τον constructor
     * και κάνει και Inject
     */
    private final IUserService userService;

    public boolean authenticate(UserLoginDTO dto) {
        return userService.isUserValid(dto.getUsername(), dto.getPassword());
    }
}

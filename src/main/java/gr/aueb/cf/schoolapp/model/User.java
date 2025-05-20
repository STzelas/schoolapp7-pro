package gr.aueb.cf.schoolapp.model;

import gr.aueb.cf.schoolapp.core.enums.RoleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.security.auth.Subject;
import java.io.Serializable;
import java.security.Principal;

/**
 * Principal λέμε τον LoggedIn χρήστη.
 * Το κάνουμε για γίνει ο χρήστης συστηματικός
 * να μπει μέσα στο σύστημα που έχουμε φτιάκει δηλαδή
 * πχ το username του ή το αφμ του, κάτι που να
 * τον μοναδικοποιεί
 * Κάνουμε implements το principal του java.security
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements Principal, IdentifiableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // auto increment
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private RoleType roleType;

    @Override
    public String getName() {
        return username;
    }
}

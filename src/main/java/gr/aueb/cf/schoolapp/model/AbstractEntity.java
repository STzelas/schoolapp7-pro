package gr.aueb.cf.schoolapp.model;


import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Θα βάλουμε τα κοινά πεδία των entities
 */

@Getter
@Setter
// JPA Annotation / όταν χαρακτηρίζουμε
// μία κλάση ως MappedSuperClass κληρονομείτε
// μόνο δε θα δημιουργηθεί Entity,
// δε θα δημιουργηθεί πίνακας
// στη βάση που να ονομαστεί abstract entity
// είναι μόνο για κληρονομικότητα
@MappedSuperclass
public abstract class AbstractEntity {

    @Column(name = "created_at", updatable = false, nullable = false)
    // @CreationTimestamp με Hibernate
    private LocalDateTime created_at;

    @Column(name = "updated_at")
    // @UpdateTimestamp με Hibernate
    private LocalDateTime updated_at;

    @Column(unique = true, updatable = false, nullable = false, length = 36)
    private String uuid = UUID.randomUUID().toString();

    /**
     * PrePersist σημαίνει πριν γίνει το persist
     * αυτόματα η java θα αρχικοποιεί η java το
     * created_at και το updated_at
     */
    @PrePersist
    protected void onCreate() {
        if (created_at == null) created_at = LocalDateTime.now();
        updated_at = LocalDateTime.now();
    }

    /**
     * Με τον ίδιο τρόπο, πριν γίνει update
     */
    @PreUpdate
    protected void onUpdate() {
        updated_at = LocalDateTime.now();
    }


}

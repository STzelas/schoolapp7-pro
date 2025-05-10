package gr.aueb.cf.schoolapp;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * Entry point κάθε jax-rs εφαρμογής,
 * μία class Που κάνει extends την Application της jakarta
 * Ορίζουμε ένα root patch με το @ApplicationPath
 * Και κάνει config τον web-server ώστε να κάνουν
 * scan όλα τα beans (πρέπει να βρίσκεται στο root
 * folder ώστε να κάνει scan τα packages και
 * τα subpackages (/api θα είναι το root)
 */
@ApplicationPath("/api")
public class SchoolApplication extends Application {
}

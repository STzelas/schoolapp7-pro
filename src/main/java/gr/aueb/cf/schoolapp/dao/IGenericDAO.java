package gr.aueb.cf.schoolapp.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Θα κάνουμε ένα Generics DAO για να μπορεί το κάθε
 * DAO να υλοποιεί απο εδώ ότι χρειάζεται (teacher, student κλπ)
 * τις CRUD πράξεις
 */
public interface IGenericDAO<T> {

    // Θα χρησιμοποιήσω Optionals γιατί
    // 1. Δεν επιστρέφουν null
    // 2. Γιατί δίνει API το Optional,
    //    που θα βοηθήσει στο service layer
    //    (getter, orElseThrow κλπ)
    Optional<T> insert(T t);
    Optional<T> update(T t);

    // Παρόλο που έχουμε Long, μπορεί αλλού
    // πχ να μην ήταν Long, οπότε βάζουμε Object id
    // να είμαστε καλυμμένοι
    void delete(Object id);

    // SELECT count *
    long count();

    // π.χ Να πάρει όλους τους teachers και μετα
    // ανα συγκεκριμένο criteria
    // αυτό γίνεται με Map<String, και object>
    long getCountByCriteria(Map<String, Object> criteria);

    Optional<T> getById(Object id);

    // find με name πχ ή lastname κλπ
    Optional<T> findByField(String fieldName, Object value);
    List<T> getAll();
    List<T> getByCriteria(Map<String, Object> criteria);

    // Αυτό το υπερφορτωμένο το κάνουμε για convenience
    // Για να βάλουμε ενα root class,
    // πιο ειδική περίπτωση του απο πάνω
    List<T> getByCriteria(Class<T> clazz, Map<String, Object> criteria);

    // Το paginated είναι αυτό που φαίνεται στο site πόσα έχει πχ η λίστα πόσα θα εμφανίσει σε ποιά σελίδα
    List<T> getByCriteriaPaginated(Class<T> clazz, Map<String, Object> criteria, Integer page, Integer size);




}

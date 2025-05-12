package gr.aueb.cf.schoolapp.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
//import lombok.*;

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor

/**
 * Τα records είναι immutable απλά στην ονομασία δεν έχει getVat αλλα -> vat
 */
public record TeacherInsertDTO (
    @NotNull(message = "Το όνομα δεν μπορεί να μην υπάρχει")
    @Size(min = 2, max = 255, message = "Το όνομα πρέπει να είναι μεταξύ 2 και 255 χαρακτήρων")   // bean validation / τα λογικά δεν γίνονται με bean
    String firstname,

    @NotNull(message = "Το επώνυμο δεν μπορεί να μην υπάρχει")
    @Size(min = 2, max = 255, message = "Το επώνυμο πρέπει να είναι μεταξύ 2 και 255 χαρακτήρων")
    String lastname,

    @NotNull(message = "Το ΑΦΜ δεν μπορεί να μην υπάρχει")
    @Size(min = 9, message = "Το ΑΦΜ πρέπει να περιέχει τουλάχιστον 9 ψηφία")
    String vat
){}

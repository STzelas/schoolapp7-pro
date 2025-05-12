package gr.aueb.cf.schoolapp.dto;

import lombok.*;

//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
@Builder
public record TeacherReadOnlyDTO(
    Long id,
    String vat,
    String firstname,
    String lastname
){}

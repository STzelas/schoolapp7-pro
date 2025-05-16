package gr.aueb.cf.schoolapp.dto;

public record ResponseMessageDTO(String code, String description) {

    /**
     * Στα records οι auxiliary constructors πρέπει να καλούν
     * τους canonical constructors (τους δίνει αυτόματα το record)
     * ο canonical είναι ο full overloaded constructors
     */
    public ResponseMessageDTO(String code) {
        this(code, ""); // auxiliary constructor
    }
}

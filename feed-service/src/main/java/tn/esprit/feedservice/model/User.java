package tn.esprit.feedservice.model;

import lombok.*;

/**
 * @author Jozef
 */
@Getter @Setter @NoArgsConstructor@AllArgsConstructor
public class User {

    private String _id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @Override
    public String toString() {
        return "User{" +
                "_id='" + _id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

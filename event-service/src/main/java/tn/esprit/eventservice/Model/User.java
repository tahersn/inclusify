package tn.esprit.eventservice.Model;

import lombok.*;

/**
 * @author oumaima1115
 */
@Getter @Setter @NoArgsConstructor@AllArgsConstructor
public class User {

    private String _id;
    private String firstName;

    public String get_id() {
        return _id;
    }

    private String lastName;
    private String email;
    private String password;
    //List events that attend

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

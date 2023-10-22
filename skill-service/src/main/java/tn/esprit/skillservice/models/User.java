package tn.esprit.skillservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Fourat
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

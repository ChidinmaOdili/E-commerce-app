package model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class User {

    private int id;
    private String address;
    private String added_date;
    private String email;
    private String gender;
    private String name;
    private String password;
    private String phone;
    private String pin_code;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}

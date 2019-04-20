package hyeyoung.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class User {

    @Id
    private String id;

    private String loginId;

    private String password;

    private String name;

    private String tel;

    private String email;

}

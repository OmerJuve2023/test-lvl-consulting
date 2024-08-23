package omer.solutions.testlvlconsulting.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "authority")
    private String authority;
}
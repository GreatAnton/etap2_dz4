package edu2.innotech;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "logins")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Logins {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Timestamp access_date;
    @ManyToOne
    @JoinColumn(name = "user_id")
    Users user;
    String application;
}

package com.jta28022018.jta.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by dsrpc on 03.03.2018.
 */
@Entity
@Table(name = "ERRORREPORT")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ErrorToSave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //@Column(unique = true, nullable = false)
    @Column(name = "EXCEPTIONTYPE", nullable = false)
    private String exceptionType;

    @Column(name = "MESSAGE", nullable = false)
    private String message;

    @CreationTimestamp
    @Column(name = "CREATED_ON", nullable = false)
    private Date createdOn;

}

package com.suncreate.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 用户实体
 */
@Entity
@Table(name = "user")
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @JsonProperty("id")
    @org.springframework.data.annotation.Id
    @Id
    private Integer id;
    @JsonProperty("phoneNumber")
    @Column(nullable = false)
    private String phoneNumber;
    @JsonProperty("username")
    @Column(nullable = false)
    private String username;
}

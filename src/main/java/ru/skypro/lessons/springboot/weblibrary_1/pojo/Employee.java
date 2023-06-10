package ru.skypro.lessons.springboot.weblibrary_1.pojo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "employee")

public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private int salary;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id")
    private Position position;


}

package com.orderFood.ThangOrderFood.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private User owner;

    private String name;

    private String description;

    private String cuisineType;

    @OneToOne
    private Address address;

    @Embedded// được sử dụng để đánh dấu một lớp là một kiểu dữ liệu có thể được nhúng vào các thực thể khác
    // cac du lieu bang ContactInformation se in vao trong table Restaurant
    private ContactInformation contactInformation;

    private String opingHours;

    @OneToMany(mappedBy = "restaurant" , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders = new ArrayList<Order>();

    @ElementCollection//tao 1 bang rieng
    //à một chú thích (annotation) trong JPA (Java Persistence API) được sử dụng để ánh xạ một
    // tập hợp các giá trị đơn giản (như List, Set, hoặc Map) không phải là thực thể (entity) vào cơ sở dữ liệu
    // . Nó thường được sử dụng để ánh xạ các loại dữ liệu như chuỗi, số, hoặc các loại dữ liệu nhúng (embeddable).
    @Column(length = 1000)
    private List<String> images;

    private LocalDateTime registrationDate;

    private boolean open;

    @JsonIgnore//ể chỉ định rằng một thuộc tính (field) của đối tượng sẽ bị bỏ
    // qua trong quá trình tuần tự hóa (serialization) và giải tuần tự hóa (deserialization) JSON.
    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL)
    private List<Food> foods= new ArrayList<>();



}

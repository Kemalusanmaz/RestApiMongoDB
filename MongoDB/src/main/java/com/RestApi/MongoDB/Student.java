package com.RestApi.MongoDB;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Data //getter,setter,constructer
@Document("student") //collection'ı temsil eder
public class Student { //Collection
    @Id
    private String id; //primary key
    private String firstName;
    private String lastName;
    @Indexed(unique = true) //email alanı üzerinde benzersiz bir dizin oluşturulmasını sağlar.
    // Bu, aynı e-posta adresine sahip öğrencilerin koleksiyonda birbirinden farklı olmalarını garanti altına alır ve
    // hızlı bir şekilde e-posta adresi üzerinden sorgulama yapılmasına imkan tanır.
    //Eğer aynı emiaş ile kullanıcı oluşturulursa spring boot dplicate key hatası verecektir.
    private String email;
    private Gender gender;
    private Address address;

    private List<String> favoriteSubjects;
    private BigDecimal totalSpentInBooks;
    private LocalDateTime created;

    //Id otomatik generate edildiği için dışarıdan erişilebilmesi için diğer değişkenler constructer blokta toplanır
    public Student(String firstName, String lastName, String email, Gender gender, Address address, List<String> favoriteSubjects, BigDecimal totalSpentInBooks, LocalDateTime created) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.address = address;
        this.favoriteSubjects = favoriteSubjects;
        this.totalSpentInBooks = totalSpentInBooks;
        this.created = created;
    }
}

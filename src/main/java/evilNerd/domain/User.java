package evilNerd.domain;

import lombok.Setter;
import lombok.Getter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.sql.Timestamp;
import java.util.Date;


@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor

public class User {
    /*Here we will store PK of m_users table*/

    private Long id;

    private String name;

    private String surname;

    private Date birthDate;

    private Gender gender = Gender.NOT_SELECTED;

    private Timestamp created = new Timestamp(System.currentTimeMillis());

    private Timestamp changed  = new Timestamp(System.currentTimeMillis());

    private Float weight;

//    @Autowired
//    @Qualifier("cars")
    // через объект
  private Cars userCars;

//  @Autowired
//  // через сеттер
//  private void setUserCars (Cars userCars){
//      this.userCars = userCars;
//  }

    @Autowired
    // через конструктор
    public User(Cars userCars){
        this.userCars = userCars;
    }


    public User(Long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }


    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }




}

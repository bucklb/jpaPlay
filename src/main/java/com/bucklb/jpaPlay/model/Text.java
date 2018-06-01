package com.bucklb.jpaPlay.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "text")
public class Text implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Lob
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Person person;

    /*
    public Text(String text,Person person){
        this.text=text;
        this.person=person;
    }
    public Text(){}
    */

    public Long getId() {        return id;    }
    public void setId(Long id) {        this.id = id;    }

    public String getText() {        return text;    }
    public void setText(String text) {        this.text = text;    }

    public Person getPerson() {        return person;    }
    public void setPerson(Person person) {        this.person = person;    }
}

package com.br.r.adopet.model.tutor;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tutors")
@NoArgsConstructor
@EqualsAndHashCode(of= "id")
@Getter
@ToString
@Setter

public class Tutor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String city;
    @Column(name = "personal_description", columnDefinition = "TEXT")
    private String personalDescription;

    public Tutor(TutorRegisterData data) {
        this.name = data.name();
        this.phoneNumber = data.phoneNumber();
        this.city = data.city();
        this.personalDescription = data.personalDescription();
    }

    public void updateTutorDataCompletely(TutorUpdate data) {
        this.name = data.name();
        this.phoneNumber = data.phoneNumber();
        this.city = data.city();
        this.personalDescription = data.personalDescription();
    }
}

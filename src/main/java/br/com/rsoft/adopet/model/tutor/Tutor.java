package br.com.rsoft.adopet.model.tutor;

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
    @Column(name = "profile_photo", columnDefinition = "TEXT")
    private String profilePhoto;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String city;
    @Column(name = "personal_description", columnDefinition = "TEXT")
    private String personalDescription;


    public Tutor(String name, String profilePhoto, String phoneNumber, String city, String personalDescription) {
        this.name = name;
        this.profilePhoto = profilePhoto;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.personalDescription = personalDescription;
    }
}

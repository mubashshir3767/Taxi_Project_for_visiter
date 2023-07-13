package uz.optimit.taxi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import uz.optimit.taxi.model.request.CarRegisterRequestDto;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String carNumber;

    private String color;

    private String texPassport;

    @ManyToOne
    private AutoModel autoModel;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Attachment> autoPhotos;

    @OneToOne(cascade = CascadeType.ALL)
    private Attachment texPassportPhoto;

    @OneToOne(cascade = CascadeType.ALL)
    private Attachment photoDriverLicense;

    @JsonIgnore
    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<AnnouncementDriver> announcementDriver;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<Seat> seatList;

    private boolean active;

    private boolean deleted;

    public static Car from(CarRegisterRequestDto carRegisterRequestDto) {
        return Car.builder()
                .color(carRegisterRequestDto.getColor())
                .texPassport(carRegisterRequestDto.getTexPassport())
                .carNumber(carRegisterRequestDto.getCarNumber())
                .active(false)
                .deleted(false)
                .build();
    }

}

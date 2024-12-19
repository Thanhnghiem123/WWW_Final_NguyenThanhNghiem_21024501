package vn.edu.iuh.fit.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "cuahang")
public class Cuahang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MACUAHANG", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "TEN", nullable = false, length = 50)
    private String ten;

    @OneToMany(mappedBy = "macuahang")
    private Set<Donhang> donhangs = new LinkedHashSet<>();

}
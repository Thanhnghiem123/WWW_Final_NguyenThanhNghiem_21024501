package vn.edu.iuh.fit.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import vn.edu.iuh.fit.enums.TrangThai;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "donhang")
public class Donhang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Size(max = 14)
    @NotNull
    @Column(name = "MADONHANG", nullable = false, length = 14)
    private String madonhang;

    @Size(max = 50)
    @Column(name = "TENKHACHHANG", length = 50)
    private String tenkhachhang;

    @Size(max = 50)
    @Column(name = "EMAIL", length = 50)
    private String email;

    @Column(name = "ngaydathang")
    private LocalDate ngaydathang;

    @Size(max = 50)
    @Column(name = "TENSP", length = 50)
    private String tensp;

    @ColumnDefault("1")
    @Column(name = "TRANGTHAI")
    @Enumerated(EnumType.ORDINAL)
    private TrangThai trangthai;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MACUAHANG")
    private Cuahang macuahang;

}
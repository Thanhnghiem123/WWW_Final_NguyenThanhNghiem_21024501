package vn.edu.iuh.fit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.models.Donhang;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface DonHangRepository extends JpaRepository<Donhang, Integer> {
    List<Donhang> findByMadonhangContainingOrMacuahang_TenContaining(String madonhang, String tenCuaHang);

    List<Donhang> findByEmailAndNgaydathang(String email, LocalDate ngaydathang);
}

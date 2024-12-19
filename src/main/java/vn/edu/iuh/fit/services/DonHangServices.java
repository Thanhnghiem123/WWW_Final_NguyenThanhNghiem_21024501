package vn.edu.iuh.fit.services;

import vn.edu.iuh.fit.models.Donhang;

import java.time.LocalDate;
import java.util.List;

public interface DonHangServices {
    List<Donhang> findAll();
    Donhang save(Donhang donhang);
    void delete(Integer id);
    List<Donhang> search(String keyword);
    List<Donhang> findByEmailAndTime(String email, LocalDate ngaydathang);
}
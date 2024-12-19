package vn.edu.iuh.fit.services.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.enums.TrangThai;
import vn.edu.iuh.fit.models.Donhang;
import vn.edu.iuh.fit.repositories.DonHangRepository;
import vn.edu.iuh.fit.services.DonHangServices;

import java.time.LocalDate;
import java.util.List;

@Service
public class DonHangmImpl implements DonHangServices {
    @Autowired
    private DonHangRepository donHangRepository;


    public List<Donhang> findAll() {
        return donHangRepository.findAll();
    }


    public Donhang save(Donhang donhang) {
        return donHangRepository.save(donhang);
    }

    public void delete(Integer id) {
        Donhang donhang = donHangRepository.findById(id).orElse(null);
        if (donhang != null && donhang.getTrangthai() == TrangThai.CHUA_CHAP_NHAN) {
            donHangRepository.delete(donhang);
        }
    }

    public List<Donhang> search(String keyword) {
        return donHangRepository.findByMadonhangContainingOrMacuahang_TenContaining(keyword, keyword);
    }


    public List<Donhang> findByEmailAndTime(String email, LocalDate ngaydathang) {
        return donHangRepository.findByEmailAndNgaydathang(email, ngaydathang);
    }
}

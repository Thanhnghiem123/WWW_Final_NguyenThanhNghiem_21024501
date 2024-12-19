package vn.edu.iuh.fit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.models.Cuahang;
import vn.edu.iuh.fit.repositories.CuaHangRepository;

import java.util.List;

@Service
public class CuaHangServices {

    @Autowired
    private CuaHangRepository cuaHangRepository;

    public List<Cuahang> findAll() {
        return cuaHangRepository.findAll();
    }

}

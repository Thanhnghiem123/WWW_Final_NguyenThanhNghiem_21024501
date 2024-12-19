package vn.edu.iuh.fit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.iuh.fit.services.CuaHangServices;

@Controller
@RequestMapping("/cuahang")
public class CuaHangController {

    @Autowired
    private CuaHangServices cuaHangServices;

    @GetMapping("/list")
    public String listCuaHang(Model model){
        model.addAttribute("listCuaHang", cuaHangServices.findAll());
        return "listCuaHang";
    }
}

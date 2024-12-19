package vn.edu.iuh.fit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.models.Cuahang;
import vn.edu.iuh.fit.models.Donhang;
import vn.edu.iuh.fit.services.CuaHangServices;
import vn.edu.iuh.fit.services.DonHangServices;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/donhang")
public class DonHangController {

    @Autowired
    private DonHangServices donHangServices;

    @Autowired
    private CuaHangServices cuaHangServices;

    @GetMapping("/list")
    public String getistDonHang(Model model ) {

        List<Donhang> listDonHang = donHangServices.findAll();
        model.addAttribute("listDonHang", listDonHang);

        return "dsDonHang";

    }
    @ModelAttribute("listCuaHang")
    public List<Cuahang> populateCuaHang() {
        return cuaHangServices.findAll();
    }
    @GetMapping("/addForm")
    public ModelAndView addForm() {
        ModelAndView mav = new ModelAndView("themDonHang");
        mav.addObject("dh", new Donhang());
        return mav;
    }

    @PostMapping("/add")
    public String addDonHang(@ModelAttribute("dh") Donhang donhang, Model model) {
        // •	Ngày đặt hàng: phải lớn hơn ngày hiện tại
        if (donhang.getNgaydathang().isBefore(LocalDate.now())) {
            model.addAttribute("ngaydathangError", "Ngày đặt hàng phải lớn hơn ngày nhận hàng");
            return "themDonHang";
        }

        // 	Trong cùng 1 ngày 1 email chỉ được đặt 1 đơn hàng tạm
        List<Donhang> listDonHang = donHangServices.findByEmailAndTime(donhang.getEmail(), donhang.getNgaydathang());
        if (listDonHang.size() > 0) {
            model.addAttribute("ngaydathangError", "Email đã đặt đơn hàng trong ngày");
            return "themDonHang";
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        donhang.setMadonhang(LocalDateTime.now().format(formatter));
        donHangServices.save(donhang);
        return "redirect:/donhang/list";
    }


    @PostMapping("/search")
    public String searchDonHang(@RequestParam("keyword") String keyword, Model model) {
        List<Donhang> listDonHang = donHangServices.search(keyword);
        model.addAttribute("listDonHang", listDonHang);
        return "dsDonHang";
    }

    @PostMapping("/delete/{id}")
    public String deleteDonHang(@PathVariable("id") Integer id) {
        donHangServices.delete(id);
        return "redirect:/donhang/list";
    }


}

package onlineshopapp.fashionstore.web.controller;

import onlineshopapp.fashionstore.service.VoucherService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/voucher")
public class VoucherController {
    private final VoucherService voucherService;

    public VoucherController(VoucherService voucherService) {
        this.voucherService = voucherService;
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public String getVoucherForm() {
        return "voucher-form";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public String createVoucher(@RequestParam String voucherName, @RequestParam int discount, Model model) {
        model.addAttribute("success",true);
        this.voucherService.create(voucherName,discount);
        return  "voucher-form";
    }
}

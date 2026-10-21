package com.bill.payment.system.ctl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bill.payment.system.dto.VendorDTO;
import com.bill.payment.system.exception.DuplicateRecordException;
import com.bill.payment.system.form.VendorForm;
import com.bill.payment.system.service.VendorServiceInt;





@Controller
@RequestMapping("/ctl/vendor")
public class VendorCtl extends BaseCtl {

	@Autowired
	private VendorServiceInt service;
	
	@ModelAttribute
	public void preload(Model model) {
		HashMap<String, String> map2 = new HashMap<String, String>();
		map2.put("Male", "Male");
		map2.put("Female", "Female");
		model.addAttribute("gender", map2);
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Electricity", "Electricity");
		map.put("Telephone", "Telephone");
		map.put("DTH", "DTH");
		map.put("Insurance", "Insurance");
		map.put("Tax", "Tax");
		map.put("Credit Card", "Credit Card");
		map.put("Loan account", "Loan account");
		model.addAttribute("type", map);
	}

	@GetMapping()
	public String display(@RequestParam(required = false) Long id, Long pId, @ModelAttribute("form") VendorForm form,
			HttpSession session, Model model) {
		if (form.getId() > 0) {
			VendorDTO bean = service.findBypk(id);
			form.populate(bean);
		}
		return "vendor";
	}
	
	
	@GetMapping("/status")
	public String displayStatus(@RequestParam(required = false) Long id,String status, @ModelAttribute("form") VendorForm form,
			HttpSession session, Model model) {
		try {
		if (form.getId() > 0) {
			VendorDTO vdDto=service.findBypk(id);
			vdDto.setStatus(status);
			service.update(vdDto);
			return "redirect:/ctl/vendor/search";
		}
		} catch (DuplicateRecordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "vendor";
	}

	@PostMapping
	public String submit(@Valid @ModelAttribute("form") VendorForm form, BindingResult bindingResult,
			HttpSession session, Model model,HttpServletRequest request) {
		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/ctl/vendor";
		}
		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {
				if (bindingResult.hasErrors()) {
					return "vendor";
				}
				VendorDTO bean = (VendorDTO) populateDTO(form.getDTO(),request);
				if (bean.getId() > 0) {
					bean.setState("Pending");
					service.update(bean);
					model.addAttribute("success", "Vendor update Successfully!!!!");
				} else {
					bean.setState("Pending");
					service.add(bean);
					model.addAttribute("success", "Vendor Added Successfully!!!!");
				}
				return "vendor";
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return "vendor";
		}
		return "";
	}

	@RequestMapping(value = "/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchList(@ModelAttribute("form") VendorForm form,
			@RequestParam(required = false) String operation, Long vid, HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/ctl/vendor/search";
		}

		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();

		if (OP_NEXT.equals(operation)) {
			pageNo++;
		} else if (OP_PREVIOUS.equals(operation)) {
			pageNo--;
		} else if (OP_NEW.equals(operation)) {
			return "redirect:/ctl/vendor";
		}

		pageNo = (pageNo < 1) ? 1 : pageNo;
		pageSize = (pageSize < 1) ? 10 : pageSize;

		if (OP_DELETE.equals(operation)) {
			pageNo = 1;
			if (form.getIds() != null) {
				for (long id : form.getIds()) {
					VendorDTO dto = new VendorDTO();
					dto.setId(id);
					service.delete(dto);
				}
				model.addAttribute("success", "Deleted Successfully!!!");
			} else {
				model.addAttribute("error", "Select at least one record");
			}
		}
		VendorDTO dto = (VendorDTO) form.getDTO();

		
		List<VendorDTO> list = service.search(dto, pageNo, pageSize);
		List<VendorDTO> totallist = service.search(dto);
		model.addAttribute("list", list);

		if (list.size() == 0 && !OP_DELETE.equalsIgnoreCase(operation)) {
			model.addAttribute("error", "Record not found");
		}

		int listsize = list.size();
		int total = totallist.size();
		int pageNoPageSize = pageNo * pageSize;

		form.setPageNo(pageNo);
		form.setPageSize(pageSize);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("listsize", listsize);
		model.addAttribute("total", total);
		model.addAttribute("pagenosize", pageNoPageSize);
		model.addAttribute("form", form);
		return "vendorList";
	}
	

}

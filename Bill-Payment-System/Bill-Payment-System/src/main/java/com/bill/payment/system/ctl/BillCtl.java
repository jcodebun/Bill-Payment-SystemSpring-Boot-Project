package com.bill.payment.system.ctl;

import java.util.Date;
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

import com.bill.payment.system.dto.BillDTO;
import com.bill.payment.system.dto.UserDTO;
import com.bill.payment.system.dto.VendorDTO;
import com.bill.payment.system.exception.DuplicateRecordException;
import com.bill.payment.system.form.BillForm;
import com.bill.payment.system.service.BillServiceInt;
import com.bill.payment.system.service.UserServiceInt;

@Controller
@RequestMapping("/ctl/bill")
public class BillCtl extends BaseCtl {

	@Autowired
	private BillServiceInt service;
	
	@Autowired
	private UserServiceInt userService;
	
	@ModelAttribute
	public void preload(Model model) {
		UserDTO udto=new UserDTO();
		udto.setRoleId(2L);
		model.addAttribute("userList",userService.search(udto));
		
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
	public String display(@RequestParam(required = false) Long id, Long pId, @ModelAttribute("form") BillForm form,
			HttpSession session, Model model) {
		if (form.getId() > 0) {
			BillDTO bean = service.findBypk(id);
			form.populate(bean);
		}
		return "bill";
	}
	

	@PostMapping
	public String submit(@Valid @ModelAttribute("form") BillForm form, BindingResult bindingResult,
			HttpSession session, Model model,HttpServletRequest request) {
		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/ctl/bill";
		}
		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {
				if (bindingResult.hasErrors()) {
					return "bill";
				}
				BillDTO bean = (BillDTO) populateDTO(form.getDTO(),request);
				VendorDTO venderDto=(VendorDTO)session.getAttribute("vendor");
				UserDTO uDto=userService.findBypk(bean.getUserId());
				bean.setDate(new Date());
				bean.setStatus("Pending");
				bean.setVendorId(venderDto.getId());
				bean.setVendor(venderDto);
				bean.setUser(uDto);
				if (bean.getId() > 0) {
					service.update(bean);
					model.addAttribute("success", "Bill update Successfully!!!!");
				} else {
					service.add(bean);
					model.addAttribute("success", "Bill Added Successfully!!!!");
				}
				return "bill";
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return "bill";
		}
		return "";
	}

	@RequestMapping(value = "/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchList(@ModelAttribute("form") BillForm form,
			@RequestParam(required = false) String operation, Long vid, HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/ctl/bill/search";
		}

		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();

		if (OP_NEXT.equals(operation)) {
			pageNo++;
		} else if (OP_PREVIOUS.equals(operation)) {
			pageNo--;
		} else if (OP_NEW.equals(operation)) {
			return "redirect:/ctl/bill";
		}

		pageNo = (pageNo < 1) ? 1 : pageNo;
		pageSize = (pageSize < 1) ? 10 : pageSize;

		if (OP_DELETE.equals(operation)) {
			pageNo = 1;
			if (form.getIds() != null) {
				for (long id : form.getIds()) {
					BillDTO dto = new BillDTO();
					dto.setId(id);
					service.delete(dto);
				}
				model.addAttribute("success", "Deleted Successfully!!!");
			} else {
				model.addAttribute("error", "Select at least one record");
			}
		}
		BillDTO dto = (BillDTO) form.getDTO();
		
		UserDTO  uDto=(UserDTO)session.getAttribute("user");
		
		if(uDto !=null) {
		if(uDto.getRoleId()==2) {
			dto.setUserId(uDto.getId());
		}
		}
		
		VendorDTO vDto=(VendorDTO)session.getAttribute("vendor");
		
		if(vDto !=null) {
				dto.setVendorId(vDto.getId());
		}
		
		
		List<BillDTO> list = service.search(dto, pageNo, pageSize);
		List<BillDTO> totallist = service.search(dto);
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
		return "billList";
	}
	

}

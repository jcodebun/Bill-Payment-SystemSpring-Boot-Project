package com.bill.payment.system.ctl;

import java.util.HashMap;
import java.util.List;

import javax.mail.Session;
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
import com.bill.payment.system.dto.InvoiceDTO;
import com.bill.payment.system.exception.DuplicateRecordException;
import com.bill.payment.system.form.InvoiceForm;
import com.bill.payment.system.service.BillServiceInt;
import com.bill.payment.system.service.InvoiceServiceInt;
import com.bill.payment.system.util.DataUtility;

@Controller
@RequestMapping("/ctl/invoice")
public class InvoiceCtl extends BaseCtl {

	@Autowired
	private InvoiceServiceInt service;
	
	@Autowired
	private BillServiceInt billService;
	
	@ModelAttribute
	public void preload(Model model) {
		
	}

	@GetMapping()
	public String display(@RequestParam(required = false) Long id, Long iId, @ModelAttribute("form") InvoiceForm form,
			HttpSession session, Model model) {
		if (form.getId() > 0) {
			InvoiceDTO bean = service.findBypk(id);
			form.populate(bean);
		}
		iId=DataUtility.getLong(String.valueOf(iId));
		if(iId>0) {
			session.setAttribute("iId",iId);
		}
		return "payment";
	}
	

	@PostMapping
	public String submit(@Valid @ModelAttribute("form") InvoiceForm form, BindingResult bindingResult,
			HttpSession session, Model model,HttpServletRequest request) {
		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/ctl/invoice";
		}
		try {
			if (OP_PAYMENT.equalsIgnoreCase(form.getOperation())) {
				InvoiceDTO bean = (InvoiceDTO) populateDTO(form.getDTO(),request);
					long iId = DataUtility.getLong(String.valueOf(session.getAttribute("iId")));
					BillDTO billDTO = billService.findBypk(iId);
					bean.setBill(billDTO);
					bean.setBillId(billDTO.getId());
					bean.setVendorType(billDTO.getVendorType());
					bean.setUser(billDTO.getUser());
					bean.setUserId(billDTO.getUserId());
					bean.setInvoiceNo(DataUtility.getRandomBooking());
					bean.setPaidAmount(billDTO.getAmount());
					billDTO.setStatus("Paid");
					service.add(bean);
					billService.update(billDTO);
					session.setAttribute("invoice",bean);
					model.addAttribute("success", "Bill Paid Successfully!!!!");
				
				return "success";
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return "invoice";
		}
		return "";
	}

	@RequestMapping(value = "/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchList(@ModelAttribute("form") InvoiceForm form,
			@RequestParam(required = false) String operation, Long vid, HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/ctl/invoice/search";
		}

		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();

		if (OP_NEXT.equals(operation)) {
			pageNo++;
		} else if (OP_PREVIOUS.equals(operation)) {
			pageNo--;
		} else if (OP_NEW.equals(operation)) {
			return "redirect:/ctl/invoice";
		}

		pageNo = (pageNo < 1) ? 1 : pageNo;
		pageSize = (pageSize < 1) ? 10 : pageSize;

		if (OP_DELETE.equals(operation)) {
			pageNo = 1;
			if (form.getIds() != null) {
				for (long id : form.getIds()) {
					InvoiceDTO dto = new InvoiceDTO();
					dto.setId(id);
					service.delete(dto);
				}
				model.addAttribute("success", "Deleted Successfully!!!");
			} else {
				model.addAttribute("error", "Select at least one record");
			}
		}
		InvoiceDTO dto = (InvoiceDTO) form.getDTO();

		
		List<InvoiceDTO> list = service.search(dto, pageNo, pageSize);
		List<InvoiceDTO> totallist = service.search(dto);
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
		return "invoiceList";
	}
	

}

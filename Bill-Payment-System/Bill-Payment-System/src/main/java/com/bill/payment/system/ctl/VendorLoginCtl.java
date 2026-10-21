package com.bill.payment.system.ctl;

import java.util.HashMap;
import java.util.logging.Logger;

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

import com.bill.payment.system.dto.UserDTO;
import com.bill.payment.system.dto.VendorDTO;
import com.bill.payment.system.exception.DuplicateRecordException;
import com.bill.payment.system.form.ChangePasswordForm;
import com.bill.payment.system.form.ForgetPasswordForm;
import com.bill.payment.system.form.MyProfileForm;
import com.bill.payment.system.form.VendorChangePasswordForm;
import com.bill.payment.system.form.VendorForgetPasswordForm;
import com.bill.payment.system.form.VendorLoginForm;
import com.bill.payment.system.form.VendorMyProfileForm;
import com.bill.payment.system.form.VendorRegistrationForm;
import com.bill.payment.system.service.VendorServiceInt;

@Controller
@RequestMapping("/vendor")
public class VendorLoginCtl extends BaseCtl {

	private Logger log = Logger.getLogger(VendorLoginCtl.class.getName());

	protected static final String OP_SIGNIN = "SignIn";
	protected static final String OP_SIGNUP = "SignUp";
	protected static final String OP_LOGOUT = "Logout";

	@Autowired
	private VendorServiceInt service;

	@GetMapping("/login")
	public String display(@ModelAttribute("form") VendorLoginForm form,
			HttpSession session, Model model) {
		log.info("LoginCtl login display method start");
		if (session.getAttribute("vendor") != null) {
			session.invalidate();
			model.addAttribute("success", "You have logout Successfully!!!");
		}
		
		log.info("LoginCtl login display method End");
		return "vendorLogin";
	}

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

	@PostMapping("/login")
	public String submit(@RequestParam String operation, HttpSession session,
			@Valid @ModelAttribute("form") VendorLoginForm form, BindingResult result, Model model) {
		log.info("LoginCtl login submit method start");
		System.out.println("In dopost  LoginCtl");

		if (OP_SIGNUP.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/vendor/us-signUp";
		}

		if (result.hasErrors()) {
			System.out.println(result);
			return "vendorLogin";
		}

		VendorDTO bean = service.authentication((VendorDTO) form.getDTO());

		if (bean != null) {
			System.out.println(bean.toString());
				session.setAttribute("vendor", bean);
				if(bean.getStatus()=="Pending") {
					model.addAttribute("error","Your Account is not approved by admin");
					return "vendorLogin";
				}else if(bean.getStatus()=="Reject") {
					model.addAttribute("error","Your Account is rejected by admin");
					return "vendorLogin";
				}
				return "redirect:/home";
		}
		model.addAttribute("error", "Login Id Password Invalid");
		log.info("LoginCtl login submit method End");
		return "vendorLogin";
	}

	@GetMapping("/signUp")
	public String display(@ModelAttribute("form") VendorRegistrationForm form, Model model) {
		log.info("LoginCtl signUp display method start");
		log.info("LoginCtl signUp display method End");
		return "vendorSignUp";
	}

	@PostMapping("/signUp")
	public String submit(@RequestParam String operation, @Valid @ModelAttribute("form") VendorRegistrationForm form,
			BindingResult bindingResult, Model model, HttpServletRequest request) {

		log.info("LoginCtl signUp submit method start");

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/vendor/signUp";
		}

		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult);
			return "vendorSignUp";
		}

		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {
				VendorDTO entity = (VendorDTO) populateDTO(form.getDTO(), request);
				System.out.println(entity.toString());
				entity.setStatus("Pending");
				service.register(entity);
				model.addAttribute("success", "Vendor Registerd Successfully!!!!");
				return "vendorSignUp";
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return "vendorSignUp";
		}

		log.info("LoginCtl signUp submit method end");
		return "vendorSignUp";
	}

	

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String displayProfile(HttpSession session, @ModelAttribute("form") VendorMyProfileForm form, Model model) {
		VendorDTO entity = (VendorDTO) session.getAttribute("vendor");
		form.populate(entity);
		return "vendorMyprofile";
	}

	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public String submitProfile(HttpSession session, @ModelAttribute("form") @Valid VendorMyProfileForm form,
			BindingResult bindingResult, @RequestParam(required = false) String operation, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/vendor/profile";
		}

		if (bindingResult.hasErrors()) {
			return "vendorMyprofile";
		}
		VendorDTO entity = (VendorDTO) session.getAttribute("user");
		entity = service.findBypk(entity.getId());
		entity.setName(form.getName());
		entity.setUserName(form.getUserName());
		entity.setEmailId(form.getEmailId());
		entity.setContactNo(form.getContactNo());
		try {
			service.update(entity);
		} catch (DuplicateRecordException e) {

		}
		model.addAttribute("success", "Profile Update successfully");

		return "vendorMyprofile";
	}

	@RequestMapping(value = "/changepassword", method = RequestMethod.GET)
	public String displayChangePassword(@ModelAttribute("form") VendorChangePasswordForm form, Model model) {
		return "vendorChangePassword";
	}

	@RequestMapping(value = "/changepassword", method = RequestMethod.POST)
	public String submitChangePassword(HttpSession session, @ModelAttribute("form") @Valid VendorChangePasswordForm form,
			BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "vendorChangePassword";
		}
		if (form.getNewPassword().equalsIgnoreCase(form.getConfirmPassword())) {

			VendorDTO dto = (VendorDTO) session.getAttribute("vendor");
			dto = service.findBypk(dto.getId());

			if (service.changePassword(dto.getId(), form.getOldPassword(), form.getNewPassword())) {
				model.addAttribute("success", "Password changed Successfully");
			} else {
				model.addAttribute("error", "Old Passowors Does not Matched");
			}
		} else {
			model.addAttribute("error", "New Password and confirm password does not matched");
		}
		return "vendorChangePassword";
	}

	@RequestMapping(value = "/forgetPassword", method = RequestMethod.GET)
	public String display(@ModelAttribute("form") VendorForgetPasswordForm form, HttpSession session, Model model) {

		System.out.println("In doget LoginCtl forgetpassword");

		return "vendorForgetPassword";

	}

	@RequestMapping(value = "/forgetPassword", method = RequestMethod.POST)
	public String display(@ModelAttribute("form") @Valid VendorForgetPasswordForm form, BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			return "vendorForgetPassword";
		}

		VendorDTO dto = service.findByUserName(form.getUserName());

		if (dto == null) {
			model.addAttribute("error", "User Name does not exist");
		}

		if (dto != null) {
			service.forgetPassword(form.getUserName());
			model.addAttribute("success", "Password has been sent to your registered Email ID!!");
		}
		return "vendorForgetPassword";
	}

}

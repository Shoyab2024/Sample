package com.donar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.donar.entity.DonarDetails;
import com.donar.repository.DonarRepository;
import com.donar.service.DonarService;

import jakarta.servlet.http.HttpSession;

@Controller
public class TestController 
{

    private final DonarRepository donarRepository;
	@Autowired
	DonarService donarService;


    TestController(DonarRepository donarRepository) {
        this.donarRepository = donarRepository;
    }
	
	
	@RequestMapping("/registrationPage")
	public String registrationPage() 
	{
		return "DonarRegistration";
	}
	
	@RequestMapping("/registration")
	public String DonarRegistration(DonarDetails donarDetails) 
	{
		donarService.DonarRegistration(donarDetails);
		//return "redirect:/getAllDonarDetails";
		return "/loginPage";
	}
	
	
	@RequestMapping("/getAllDonarDetails")
	public String getAllDonarDetails(Model model) 
	{
		List<DonarDetails> allDonarDetails=donarService.allDonarDetails();
		model.addAttribute("allDonarDetails", allDonarDetails);
		return "DisplayAllDonarDetails";
	}
	
	@RequestMapping("/loginPage")
	public String logingPage() 
	{
		return "LoginPage";
	}
	
	@RequestMapping("/login")
	public String forLogin(String emailid,String password,Model model,HttpSession session) 
	{
//		System.out.println(emailid);
//		System.out.println(password);
		DonarDetails donarDetails=donarService.selectDonarDetails(emailid, password);
		if (donarDetails!=null) 
		{
			// Store the DonarDetails in session if login is successful
            session.setAttribute("donarDetails", donarDetails);
            model.addAttribute("donarDetails", donarDetails);
			
			return "/BloodOperations";
			//return "DisplaySingleDonarDetails";
		} 
		else 
		{
			return "/login";
		}
	}
	
	//@ResponseBody
	@RequestMapping("/singleDonarDetails")
	public String singleDonarDetails(Model model,HttpSession session) 
	{
		
		DonarDetails donarDetails = (DonarDetails) session.getAttribute("donarDetails");
      
		model.addAttribute("donar", donarDetails);
        return "DisplaySingleDonarDetails";  // Display the donor details page
		
	}
	
	//@ResponseBody
	@RequestMapping("/update")
	public String updateDonarDetalis(DonarDetails donarDetails,Model model) 
	{
		donarService.updateDonarDetails(donarDetails);
		
		String msg="Thank You For Saving Life";
		
		model.addAttribute("msg",msg);
		
		return "MessagePage";
	}
	
	@RequestMapping("/searchWithBloodGroupOrAddress")
	public String searchWithBloodGroupOrAddress(@RequestParam("BloodGroupAdress") String input,Model model) 
	{
		List<DonarDetails> allDonarDetails=donarService.searchWithBloodGroupOrAddress(input);
		
		model.addAttribute("allDonarDetails", allDonarDetails);
		return "DisplayAllDonarDetails";
	}
	
	@RequestMapping("/searchBloodGroupAndAddress")
	public String searchWithBloodGroupAndAddress(String bloodgroup,String address,Model model) 
	{
		List<DonarDetails> allDonarDetails=donarService.searchWithBloodGroupAndAddress(bloodgroup, address);
		
		model.addAttribute("allDonarDetails", allDonarDetails);
		return "DisplayAllDonarDetails";
	}

}

/*
 * 
 * @RequestMapping ---to get add Spring web dependency
 */

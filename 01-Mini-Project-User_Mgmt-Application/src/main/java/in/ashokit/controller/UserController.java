package in.ashokit.controller;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.ashokit.entities.UserAccount;
import in.ashokit.service.UserAccountService;

@Controller
public class UserController {
	
	@Autowired
	private UserAccountService userservice;
	
	@GetMapping("/")
	public String index(Model model) {
	  model.addAttribute("user", new UserAccount());
		return "index";
	}
	
	//for send again "user"object to binding use ModelAttribute so to capture data
	@PostMapping("/save-user")
	public String handleSubmit(@ModelAttribute("user") UserAccount user, Model model) {
	  String msg  =	userservice.saveOrUpdateUserAcc(user);
		model.addAttribute("msg", msg);
		model.addAttribute("user", new UserAccount());
		return "index";
	}
	
	@GetMapping("/users")
	public String getUsers(Model model) {
	List<UserAccount> usersList= userservice.getAllUserAccounts();
	model.addAttribute("users", usersList);
	return "view-users";
	}
	
	@GetMapping("/edit")
	public String editUser(@RequestParam Integer id, Model model) {
		UserAccount userAcc = userservice.getUserAcc(id);
		 model.addAttribute("user", userAcc);
		return"index";
	}
	
	@GetMapping("/delete")
	public String deleteUser(@RequestParam("id") Integer uid, Model model) {
		boolean deleteUserAcc= userservice.deleteUserAcc(uid);
		model.addAttribute("msg","User Record Deleted");
		return "forward:/users";
	}
	
	@GetMapping("/update")
	public String statusUpdate(@RequestParam("id") Integer uid,
			@RequestParam("status") String status, Model model) {
		userservice.updateUserAccStatus(uid, status);
		
		if("Y".equals(status)) {
			model.addAttribute("msg", "User Account Activated");
		}else {
			model.addAttribute("msg", "User Account De-Activated");
		}
		return "forward:/users";
	}
    
}

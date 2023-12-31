package cmpt276.assn2.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cmpt276.assn2.models.User;
import cmpt276.assn2.models.UserRepository;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class UsersController {

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/users/view")
    public String getAllUsers(Model model) {
        System.out.println("Get all users");
        // get all users from database
        List<User> users = userRepo.findAll();
        // end of detabase call 
        for (User user : users) {
            System.out.println("Name: " + user.getName());
            System.out.println("Weight: " + user.getWeight());
            System.out.println("color: " + user.getColor());
            System.out.println("--------------------");
        }
        model.addAttribute("us", users);
        return "users/showAll";
    }
    
    @PostMapping("/users/add")
    public String addUser(@RequestParam Map<String, String> newuser, HttpServletResponse response) {
        System.out.println("Add user");
        String newName = newuser.get("name");
        Integer newWeight = Integer.parseInt(newuser.get("weight"));
        Integer newHeight = Integer.parseInt(newuser.get("height"));
        String newColor = newuser.get("color");
        Double newGpa= Double.parseDouble(newuser.get("gpa"));
        userRepo.save(new User(newName, newWeight, newHeight, newColor, newGpa));
        response.setStatus(201);
        return "redirect:/users/view";  
    }

    @DeleteMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, HttpServletResponse response) {
        System.out.println("Delete user");
        userRepo.deleteById(id);
        response.setStatus(201);
        return "users/showAll";
    }

    

}

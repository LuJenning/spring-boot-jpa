package com.example.demo.controller;

import com.example.demo.dao.UserRepository;
import com.example.demo.modal.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Controller
@Transactional
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Resource
    private UserService userService;


    @RequestMapping("/save")
    public String saveUser(User user,@RequestParam String username,@RequestParam String password,@RequestParam Integer age){
        userRepository.save(user);
        return "redirect:/list";
    }

    /*@RequestMapping("/list")
    public String list(Model model){
        List<User> users = userService.userList();
        model.addAttribute("users",users);
        return "user/userList";
    }*/

    @RequestMapping("/toEdit")
    public String toEdit(Model model,Long id) {
        User user=userService.findUserById(id);
        model.addAttribute("user", user);
        return "user/userEdit";
    }

    @RequestMapping("/edit")
    public String edit(User user){
        userService.edit(user);
        return "redirect:/list";
    }

    @RequestMapping("/delete")
    public String delete(Long id) {
        userService.delete(id);
        return "redirect:/list";
    }

    @RequestMapping("/list")
    public String findUserNoQuery(ModelMap modelMap, @RequestParam(value = "page", defaultValue = "0") Integer page,
                                  @RequestParam(value = "size", defaultValue = "5") Integer size){
        Page<User> datas = userService.findUserNoCriteria(page, size);
        modelMap.addAttribute("datas", datas);
        return "user/userList";
    }

    /*@RequestMapping(value = "/list",method = {RequestMethod.GET,RequestMethod.POST})
    public String findBookQuery(ModelMap modelMap, @RequestParam(value = "page", defaultValue = "0") Integer page,
                                @RequestParam(value = "size", defaultValue = "5") Integer size, User user){
        Page<User> datas = userService.findUserCriteria(page,size,user);
        modelMap.addAttribute("datas", datas);
        return "user/userList";
    }*/
}

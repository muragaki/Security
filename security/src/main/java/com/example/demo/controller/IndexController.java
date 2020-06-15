package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j
public class IndexController {
	  private final AccountService accountService;

	  public IndexController(AccountService accountService) {
	    this.accountService = accountService;
	  }

	  /**
	   * �g�b�v�y�[�W
	   *
	   * @param signupForm �T�C���A�b�v�t�H�[���f�[�^
	   * @param model ���f���i���[�U�[���X�g�j
	   * @return index
	   */
	  @GetMapping(value = "/")
	  public String index(@ModelAttribute("signup") SignupForm signupForm, Model model) {
	    List<User> userList = accountService.findAll();
	    model.addAttribute("users", userList);
	    return "index";
	  }

	  /**
	   * �A�J�E���g�o�^����
	   *
	   * @param signupForm �T�C���A�b�v�t�H�[���f�[�^
	   * @param redirectAttributes ���_�C���N�g��փ��b�Z�[�W�𑗂邽��
	   * @return index (redirect)
	   */
	  @PostMapping(value = "signup")
	  public String signup(@ModelAttribute("signup") SignupForm signupForm, RedirectAttributes redirectAttributes) {
	    // TODO �b��I��2�̃��[����t�^����
	    String[] roles = {"ROLE_USER", "ROLE_ADMIN"};
	    accountService.register(signupForm.getName(), signupForm.getEmail(), signupForm.getPassword(), roles);
	    redirectAttributes.addFlashAttribute("successMessage", "�A�J�E���g�̓o�^���������܂���");
	    return "redirect:/";
	  }
}

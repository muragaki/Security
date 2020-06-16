package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j
public class IndexController {
	  private final AccountService accountService;
	  String MAV_ERRORS = "errors";
	  public IndexController(AccountService accountService) {
	    this.accountService = accountService;
	  }

	  /**
	   * トップページ
	   *
	   * @param signupForm サインアップフォームデータ
	   * @param model モデル（ユーザーリスト）
	   * @return index
	   */
	  @GetMapping(value = "/")
	  public String index(@ModelAttribute("signup") SignupForm signupForm, Model model) {
	    List<User> userList = accountService.findAll();
	    model.addAttribute("users", userList);
	    return "index";
	  }

	  /**
	   * アカウント登録処理
	   *
	   * @param signupForm サインアップフォームデータ
	   * @param redirectAttributes リダイレクト先へメッセージを送るため
	   * @return index (redirect)
	   */
	  @PostMapping(value = "signup")
	  public String signup(@Validated @ModelAttribute("signup") SignupForm signupForm, BindingResult br, RedirectAttributes redirectAttributes) {
		if (br.hasErrors()) {
			setFlashAttributeErrors(redirectAttributes, br);
			return "redirect:/";
		}
	    // TODO 暫定的に2つのロールを付与する
	    String[] roles = {"ROLE_USER", "ROLE_ADMIN"};
	    accountService.register(signupForm.getName(), signupForm.getEmail(), signupForm.getPassword(), roles);
	    redirectAttributes.addFlashAttribute("successMessage", "アカウントの登録が完了しました");
	    return "redirect:/";
	  }
	    /**
	     * リダイレクト先に入力エラーを渡します。
	     *
	     * @param attributes
	     * @param result
	     */
	    public void setFlashAttributeErrors(RedirectAttributes attributes, BindingResult result) {
	        attributes.addFlashAttribute(MAV_ERRORS, result);
	    }
}

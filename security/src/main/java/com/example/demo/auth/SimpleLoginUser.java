package com.example.demo.auth;

import com.example.demo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * ���[�U�[�F�؏��
 */
@Slf4j
public class SimpleLoginUser extends org.springframework.security.core.userdetails.User {
	  // DB��茟������user�G���e�B�e�B
	  // �F�؁E�F�ȊO�ŃA�v���P�[�V�������痘�p�����̂Ńt�B�[���h�ɒ�`
	  private User user;

	  /**
	   * �f�[�^�x�[�X��茟������user�G���e�B�e�B���Spring Security�Ŏg�p���郆�[�U�[�F�؏���
	   * �C���X�^���X�𐶐�����
	   *
	   * @param user user�G���e�B�e�B
	   */
	  public SimpleLoginUser(User user) {
	    super(user.getEmail(), user.getPassword(), user.getEnable(), true, true,
	        true, convertGrantedAuthorities(user.getRoles()));
	    this.user = user;
	  }

	  public User getUser() {
	    return user;
	  }

	  /**
	   * �J���}��؂�̃��[����SimpleGrantedAuthority�̃R���N�V�����֕ϊ�����
	   *
	   * @param roles �J���}��؂�̃��[��
	   * @return SimpleGrantedAuthority�̃R���N�V����
	   */
	  static Set<GrantedAuthority> convertGrantedAuthorities(String roles) {
	    if (roles == null || roles.isEmpty()) {
	      return Collections.emptySet();
	    }
	    Set<GrantedAuthority> authorities = Stream.of(roles.split(","))
	        .map(SimpleGrantedAuthority::new)
	        .collect(Collectors.toSet());
	    return authorities;
	  }


}

package com.example.demo.auth;

import com.example.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class SimpleUserDetailsService implements UserDetailsService {
	  private final UserRepository userRepository;

	  public SimpleUserDetailsService(UserRepository userRepository) {
	    this.userRepository = userRepository;
	  }

	  /**
	   * ���[���A�h���X�Ō����������[�U�[��user�G���e�B�e�B��SimpleLoginUser�N���X�̃C���X�^���X�֕ϊ�����
	   *
	   * @param email �������郆�[�U�[�̃��[���A�h���X
	   * @return ���[���A�h���X�Ō����ł������[�U�[�̃��[�U�[���
	   * @throws UsernameNotFoundException ���[���A�h���X�Ń��[�U�[�������ł��Ȃ������ꍇ�ɃX���[����B
	   */
	  @Transactional(readOnly = true)
	  @Override
	  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	    assert(email != null);
	    log.debug("loadUserByUsername(email):[{}]", email);
	    return userRepository.findByEmail(email)
	        .map(SimpleLoginUser::new)
	        .orElseThrow(() -> new UsernameNotFoundException("User not found by email:[" + email + "]"));
	  }

}

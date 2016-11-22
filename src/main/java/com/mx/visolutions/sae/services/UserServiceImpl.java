package com.mx.visolutions.sae.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mx.visolutions.sae.dto.SignupForm;
import com.mx.visolutions.sae.dto.UserDetailsImpl;
import com.mx.visolutions.sae.dto.UserForm;
import com.mx.visolutions.sae.entities.User;
import com.mx.visolutions.sae.repositories.UserRepository;

@Service
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class UserServiceImpl implements UserService, UserDetailsService{

	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder){
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void signup(SignupForm signupForm) {
		User user = new User();
		user.setEmail(signupForm.getEmail());
		user.setUsuario(signupForm.getName());
		user.setPassword(passwordEncoder.encode(signupForm.getPassword()));
		
		userRepository.save(user);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsuario(username);
		if(user == null)
			throw new UsernameNotFoundException(username);
		
		return new UserDetailsImpl(user);
			
		
	}

	@Override
	public User findUserById(int usuarioId) {
		// TODO Auto-generated method stub
		return userRepository.findUserById(usuarioId);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public int deleteUserById(int usuarioId) {
		// TODO Auto-generated method stub
		return userRepository.deleteById(usuarioId);
	}

	@Override
	public void newUser(UserForm userForm) {
		User user = new User();
		user.setEmail(userForm.getEmail());
		user.setUsuario(userForm.getName());
		user.setPassword(passwordEncoder.encode(userForm.getPassword()));
		
		userRepository.save(user);
	}

}

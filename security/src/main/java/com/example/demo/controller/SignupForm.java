package com.example.demo.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupForm {
	  @NotBlank
	  private String email;
	  @NotBlank
	  private String password;
	  @NotBlank
	  private String name;
}

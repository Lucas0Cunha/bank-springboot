package com.bank.bank.dto;

import com.bank.bank.models.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {}

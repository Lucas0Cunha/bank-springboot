package com.bank.bank.dto;

import com.bank.bank.models.Agencias;
import com.bank.bank.models.Cliente;

public record ContaTestDTO (Long id, double saldo, String numero, Agencias agencias, Cliente cliente) {}

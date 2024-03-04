package com.workintech.ecommerce.dto;

import com.workintech.ecommerce.entity.Store;

public record RegistrationUser(String name,String email, String password, Long role_id, Store store) {
}

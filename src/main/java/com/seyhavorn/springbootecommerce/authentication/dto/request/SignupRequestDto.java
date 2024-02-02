package com.seyhavorn.springbootecommerce.authentication.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SignupRequestDto {
    private String username;
    private String password;
    private String first_name;
    private String last_name;
    private String email;
    private Map<String,Object> user_object;
}

package com.nhanph.contact.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * DTO for {@link com.nhanph.contact.entity.Contact}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactDto {
    private Long id;
    @NotBlank
    private String name;
    @Email
    @NotBlank
    private String email;
    @Pattern(regexp = "\\d{10}")
    @NotBlank
    private String phone;
    @NotBlank
    private String postal;


}
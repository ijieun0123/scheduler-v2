package com.example.schedulerv2.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class SaveUserRequestDto {

    @NotBlank(message = "이름을 입력하세요.")
    @Size(min = 2, max = 10, message = "2 ~ 10 자를 입력하세요.")
    private final String username;

    @NotBlank(message = "이메일을 입력하세요.")
    @Email(message = "유효한 이메일 형식이 아닙니다.")
    private final String email;

    @NotBlank(message = "비밀번호를 입력하세요.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z0-9$@$!%*#?&]{8,20}$", message = "영문, 숫자, 특수문자 조합으로 8 ~ 20 자리 입력하세요.")
    private final String password;

    public SaveUserRequestDto(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}

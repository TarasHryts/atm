package com.team6.atm.atm.dto;

import com.team6.atm.atm.entity.User;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    @NotNull
    @NotEmpty
    private User user;
    @NotNull
    @NotEmpty
    private Long balance;
    @NotNull
    @NotEmpty
    private String pin;
}

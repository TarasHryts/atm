package com.team6.atm.atm.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WithdrawMoneyDto {
    @NotNull
    @NotEmpty
    private Long accountId;
    @NotNull
    @NotEmpty
    private Long atmId;
    @NotNull
    @NotEmpty
    private Long amount;
}

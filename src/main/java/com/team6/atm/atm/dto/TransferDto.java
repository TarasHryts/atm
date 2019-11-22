package com.team6.atm.atm.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferDto {
    @NotNull
    @NotEmpty
    private Long fromAccount;
    @NotNull
    @NotEmpty
    private Long toAccount;
    @NotNull
    @NotEmpty
    private Long amount;
}

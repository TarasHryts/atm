package com.team6.atm.atm.dto;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepositMoneyInAtmDto {
    @NotNull
    @NotEmpty
    private Long accountId;
    @NotNull
    @NotEmpty
    private Long atmId;
    private List<BanknotesDto> banknotesList;
}

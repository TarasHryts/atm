package com.team6.atm.atm.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BanknotesDto {
    @NotEmpty
    @NotNull
    private Long value;

    @NotEmpty
    @NotNull
    private Long amount;

    public BanknotesDto(@NotEmpty @NotNull Long value, @NotEmpty @NotNull Long amount) {
        this.value = value;
        this.amount = amount;
    }
}

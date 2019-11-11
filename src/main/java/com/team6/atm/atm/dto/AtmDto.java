package com.team6.atm.atm.dto;

import com.team6.atm.atm.entity.Banknotes;
import java.util.List;
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
public class AtmDto {
    @NotNull
    @NotEmpty
    private List<Banknotes> banknotesList;
}

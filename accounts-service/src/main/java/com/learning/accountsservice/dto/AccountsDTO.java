package com.learning.accountsservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Schema(
        name = "Accounts",
        description = "Schema to hold Account information"
)
public class AccountsDTO {
    @Schema(
            description = "Account Number of SecureBank account", example = "3454433243"
    )
    @NotEmpty(message = "Account Number cannot be empty")
    @Pattern(regexp = "($|[0-9]{10})", message = "Mobile must have only 10 digits")
    private Long accountNumber;
    @NotEmpty(message = "Account Number cannot be empty")
    @Schema(
            description = "Account type of SecureBank account", example = "Savings"
    )
    private String accountType;
    @NotEmpty(message = "Account Number cannot be empty")
    @Schema(
            description = "SecureBank branch address", example = "123 NewYork"
    )
    private String branchAddress;
}

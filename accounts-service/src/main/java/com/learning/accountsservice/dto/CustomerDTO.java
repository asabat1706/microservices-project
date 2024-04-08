package com.learning.accountsservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account information"
)
public class CustomerDTO {
    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 5, max = 30, message = "Name must have a length between 5 and 30 chars")
    @Schema(description = "Name of the customer", example = "John Doe")
    private String name;
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email must be valid")
    @Schema(description = "Email address of the customer", example = "john@doe.com")
    private String email;
    @NotEmpty(message = "Mobile Number cannot be empty")
    @Pattern(regexp = "($|[0-9]{10})", message = "Mobile must have only 10 digits")
    @Schema(description = "Mobile Number of the customer", example = "9345432123")
    private String mobileNumber;
    @Schema(description = "Account details of the Customer")
    private AccountsDTO accountsDTO;
}

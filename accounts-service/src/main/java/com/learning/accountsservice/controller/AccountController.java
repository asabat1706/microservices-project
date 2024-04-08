package com.learning.accountsservice.controller;

import com.learning.accountsservice.dto.CustomerDTO;
import com.learning.accountsservice.dto.ErrorResponseDTO;
import com.learning.accountsservice.dto.ResponseDTO;
import com.learning.accountsservice.service.IAccountService;
import com.learning.accountsservice.util.AccountConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD REST APIs for Accounts in SecureBank",
        description = "CRUD REST APIs in SecureBank to CREATE, UPDATE, FETCH AND DELETE account details"
)
@RestController
@RequestMapping(path="/account-api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated
public class AccountController {

    IAccountService accountService;

    @Operation(
            summary = "Create Account REST API",
            description = "REST API to create new Customer &  Account inside SecureBank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    @PostMapping("/createAccount")
    public ResponseEntity<ResponseDTO> createAccount(@RequestBody @Valid CustomerDTO ipcustomerDTO){
        accountService.createAccount(ipcustomerDTO);
        return ResponseEntity.
                status(HttpStatus.CREATED).
                body(new ResponseDTO(AccountConstants.STATUS_201.message, AccountConstants.MESSAGE_201.message));
    }

    @Operation(
            summary = "Fetch Account Details REST API",
            description = "REST API to fetch Customer &  Account details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    @GetMapping("fetch/customerDetails")
    public ResponseEntity<CustomerDTO> fetchCustomerDetails(@RequestParam @Pattern(regexp = "($|[0-9]{10})", message = "Mobile must have only 10 digits")
                                                                        String mobileNumber){
        CustomerDTO cutomer = accountService.fetchCustomerDetails(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(cutomer);
    }
    @Operation(
            summary = "Update Account Details REST API",
            description = "REST API to update Customer &  Account details based on a account number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    @PutMapping("/updateAccount")
    public ResponseEntity<ResponseDTO> updateAccountDetails(@RequestBody @Valid CustomerDTO customerDto) {
        boolean isUpdated = accountService.updateAccount(customerDto);
        if(isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(AccountConstants.STATUS_200.message, AccountConstants.MESSAGE_200.message));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDTO(AccountConstants.STATUS_417.message, AccountConstants.MESSAGE_417_UPDATE.message));
        }
    }

    @Operation(
            summary = "Delete Account & Customer Details REST API",
            description = "REST API to delete Customer &  Account details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    @DeleteMapping("/deleteAccount")
    public ResponseEntity<ResponseDTO> deleteAccountDetails(@RequestParam @Pattern(regexp = "($|[0-9]{10})", message = "Mobile must have only 10 digits") String mobileNumber) {
        boolean isDeleted = accountService.deleteAccount(mobileNumber);
        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(AccountConstants.STATUS_200.message, AccountConstants.MESSAGE_200.message));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDTO(AccountConstants.STATUS_500.message, AccountConstants.MESSAGE_500.message));
        }
    }
}

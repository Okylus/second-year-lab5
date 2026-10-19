package ru.arkhipov.MySecondTestAppSpringBoot.controller;

import java.util.Date;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.arkhipov.MySecondTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.arkhipov.MySecondTestAppSpringBoot.exception.ValidationFailedException;
import ru.arkhipov.MySecondTestAppSpringBoot.model.Codes;
import ru.arkhipov.MySecondTestAppSpringBoot.model.ErrorCodes;
import ru.arkhipov.MySecondTestAppSpringBoot.model.ErrorMessages;
import ru.arkhipov.MySecondTestAppSpringBoot.model.Request;
import ru.arkhipov.MySecondTestAppSpringBoot.model.Response;
import ru.arkhipov.MySecondTestAppSpringBoot.service.ModifyRequestService;
import ru.arkhipov.MySecondTestAppSpringBoot.service.ModifyResponseService;
import ru.arkhipov.MySecondTestAppSpringBoot.service.ValidationService;
import ru.arkhipov.MySecondTestAppSpringBoot.util.DateTimeUtil;

@Slf4j 
@RestController
public class MyController {

    private final ValidationService validationService;

    private final ModifyResponseService modifyResponseService;
    private final ModifyRequestService modifyRequestService;

    public MyController(ValidationService validationService,
                        @Qualifier ("ModifySystemTimeResponseService") ModifyResponseService modifyResponseService,
                        @Qualifier ("ModifySystemNameRequestService") ModifyRequestService modifyRequestService) {
        this.validationService = validationService;
        this.modifyResponseService = modifyResponseService;
        this.modifyRequestService = modifyRequestService;
    }
    @PostMapping (value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request,
                                                        BindingResult bindingResult) {  

        log.info("Received request: {}", request);

        if (bindingResult.hasErrors()) {
            log.error("Binding validation failed for request {}. Errors: {}",
                    request,
                    bindingResult.getAllErrors());
        }
            
        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(DateTimeUtil.getDateTimeFormatter().format(new Date()))
                .code(Codes.SUCCESS.getName())
                .errorCode(ErrorCodes.EMPTY.getName())
                .errorMessage(ErrorMessages.EMPTY.getName())
                .build();

        log.info("Initial response created: {}", response);

            try {
                validationService.isValid(bindingResult);
                log.info("Request validation passed for uid {} and operationUid {}.", request.getUid(), request.getOperationUid());
                if ("123".equals(request.getUid())) {
                    log.error("Unsupported uid detected: {}", request.getUid());
                    throw new UnsupportedCodeException("uid 123 не поддерживается");
                }
                modifyRequestService.modify(request);
            } catch (UnsupportedCodeException e) {
                log.error("Unsupported code exception for request {}: {}", request, e.getMessage(), e);
                response.setCode(Codes.FAILED.getName());
                response.setErrorCode(ErrorCodes.UNSUPPORTED_EXCEPTION.getName());
                response.setErrorMessage(ErrorMessages.UNSUPPORTED.getName());
                log.info("Failed response for unsupported code: {}", response);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            } catch (ValidationFailedException e) {
                log.error("Validation failed for request {}: {}", request, e.getMessage(), e);
                response.setCode(Codes.FAILED.getName());
                response.setErrorCode(ErrorCodes.VALIDATION_EXCEPTION.getName());
                response.setErrorMessage(ErrorMessages.VALIDATION_ERROR.getName());
                log.info("Failed response for validation error: {}", response);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                log.error("Unexpected exception while processing request {}: {}", request, e.getMessage(), e);
                response.setCode(Codes.FAILED.getName());
                response.setErrorCode(ErrorCodes.UNKNOWN_EXCEPTION.getName());
                response.setErrorMessage(ErrorMessages.UNKNOWN.getName());
                log.info("Failed response for unknown exception: {}", response);
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

        log.info("Applying response modification to response: {}", response);
        Response modifiedResponse = modifyResponseService.modify(response);
        log.info("Final response before return: {}", modifiedResponse);

        return new ResponseEntity<>(modifiedResponse, HttpStatus.OK);
    }
}
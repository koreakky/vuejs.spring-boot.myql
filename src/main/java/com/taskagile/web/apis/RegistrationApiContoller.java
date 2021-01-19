package com.taskagile.web.apis;

import com.taskagile.domain.application.UserService;
import com.taskagile.domain.model.user.EmailAddressExistsException;
import com.taskagile.domain.model.user.RegistrationException;
import com.taskagile.domain.model.user.UsernameExistsException;
import com.taskagile.web.payload.RegistrationPayload;
import com.taskagile.web.results.ApiResult;
import com.taskagile.web.results.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
public class RegistrationApiContoller {
    private UserService service;

    public RegistrationApiContoller(UserService service) {
        this.service = service;
    }

    @PostMapping("/api/registrations")
    public ResponseEntity<ApiResult> register(@Valid @RequestBody RegistrationPayload payload){
        try{
            service.register(payload.toCommand());
            return Result.created();
        }catch(RegistrationException e){
            String errorMessage = "Registration failed";
            if(e instanceof UsernameExistsException) {
                errorMessage = "Username aleardy exists";
            }else if(e instanceof EmailAddressExistsException){
                errorMessage = "Email address already exists";
            }
            return Result.failure(errorMessage);
        }
    }
}

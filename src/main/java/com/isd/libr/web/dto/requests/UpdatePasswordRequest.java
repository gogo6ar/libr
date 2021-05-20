package com.isd.libr.web.dto.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UpdatePasswordRequest {
    private String newPassword;

    @JsonCreator
    public UpdatePasswordRequest(@JsonProperty String newPassword) {
        this.newPassword = newPassword;
    }
}

package com.nationale.account.nnTask.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

public abstract class BaseApiController {
    protected abstract String getVersion();

    @ModelAttribute
    public void validateVersion(@PathVariable String version) {
        if (!version.equals(getVersion())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Version not supported");
        }
    }

}

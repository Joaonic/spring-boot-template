package com.template.spring.core.web;

import com.template.spring.core.web.json.Json;
import flexjson.JSONSerializer;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public abstract class BaseController {

    protected ResponseEntity<Json> renderJSON(Object model) {

        return new ResponseEntity<>(new Json(model), HttpStatus.OK);

    }

    protected ResponseEntity<Json> renderJSON(Object model, JSONSerializer jsonSerializer) {

        return new ResponseEntity<>(new Json(model, jsonSerializer), HttpStatus.OK);

    }
    protected ResponseEntity<Json> renderJSON(Object model, JSONSerializer jsonSerializer, HttpStatus httpStatus) {

        return new ResponseEntity<>(new Json(model, jsonSerializer), httpStatus);

    }


    protected ResponseEntity<Boolean> ok() {
        return ResponseEntity.ok(true);
    }

}


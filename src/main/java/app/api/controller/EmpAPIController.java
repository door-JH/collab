package app.api.controller;

import app.repository.EmpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class EmpAPIController {

    private final EmpRepository empRepository;


}

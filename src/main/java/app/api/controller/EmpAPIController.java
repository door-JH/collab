package app.api.controller;

import app.entity.Emp;
import app.repository.EmpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class EmpAPIController {

    private final EmpRepository empRepository;

    @GetMapping("/api/emps")
    public ResponseEntity<?> GetAllEmps() {

        List<Emp> emps = empRepository.findAll();

        if(emps.isEmpty()) {
            Map<String, Object> response = new HashMap<>();
            response.put("mgs", "사원정보가 존재하지 않습니다");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        return ResponseEntity.ok(emps);
    }
}

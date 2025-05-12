package app.api.controller;

import app.entity.Emp;
import app.repository.EmpRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class EmpAPIController {

    private final EmpRepository empRepository;
    
    @GetMapping("/api/emp/{empno}")
    public Emp getEmpByempno(@PathVariable("empno") Integer empno) {
		return empRepository.findById(empno)
                .orElseThrow(() -> new EntityNotFoundException("msg : 사원정보가 존재하지 않습니다"));
    }


}

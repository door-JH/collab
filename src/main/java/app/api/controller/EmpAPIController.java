package app.api.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Emp;
import app.repository.DeptRepository;
import app.repository.EmpRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class EmpAPIController {

    private final EmpRepository empRepository;
    private final DeptRepository deptRepository;
    
    @GetMapping("/api/emp/{empno}")
    public Emp getEmpByempno(@PathVariable("empno") Integer empno) {
        return empRepository.findById(empno)
                .orElseThrow(() -> new EntityNotFoundException("msg : 사원정보가 존재하지 않습니다"));
    }
    
	
	@PostMapping("/api/emp")
	public ResponseEntity<?> createEmp(@RequestBody Emp emp) {
	    empRepository.save(emp);
	    return ResponseEntity.status(HttpStatus.CREATED).body(emp);
	}
		

    
   }




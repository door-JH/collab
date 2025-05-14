package app.api.controller;

import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Emp;
import app.repository.EmpRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class EmpAPIController {

    private final EmpRepository empRepository;
    
    @PutMapping("/api/emp/{id}")
    public ResponseEntity<Emp> updateEmp(@PathVariable Integer id, @RequestBody Emp updatedEmp) {
    	Emp emp = empRepository.findById(id).get();
    	
        emp.updateEmp(updatedEmp.getEname(), 
        		updatedEmp.getJob(), 
        		updatedEmp.getMgr(), 
        		updatedEmp.getHiredate(), 
        		updatedEmp.getSal(), 
        		updatedEmp.getComm(), 
        		updatedEmp.getDept());
        
        empRepository.save(emp);
        
        return ResponseEntity.ok(emp);
    }
    @GetMapping("/api/emp/{empno}")
    public Emp getEmpByempno(@PathVariable("empno") Integer empno) {
		return empRepository.findById(empno)
                .orElseThrow(() -> new EntityNotFoundException("msg : 사원정보가 존재하지 않습니다"));
    }

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

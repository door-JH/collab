package app.api.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Emp;
import app.repository.EmpRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class EmpAPIController {

    private final EmpRepository empRepository;
    
    @PutMapping("/api/emp/{id}")
    public ResponseEntity<Emp> updateEmp(@PathVariable Integer id, @RequestBody Emp updatedEmp) {
        System.out.println("update start");
    	Emp emp = empRepository.findById(id).get();
    	
        emp.updateEmp(updatedEmp.getEmpno(),
        		updatedEmp.getEname(), 
        		updatedEmp.getJob(), 
        		updatedEmp.getMgr(), 
        		updatedEmp.getHiredate(), 
        		updatedEmp.getSal(), 
        		updatedEmp.getComm(), 
        		updatedEmp.getDept());
        
        empRepository.save(emp);
        System.out.println(emp);
        
        return ResponseEntity.ok(emp);
    }

}

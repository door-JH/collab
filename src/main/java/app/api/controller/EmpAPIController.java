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
    public ResponseEntity<?> addEmp(@RequestBody Map<String, Object> req) {

        Integer deptno = (Integer) req.get("deptno");
        var dept = deptRepository.findById(deptno).orElse(null);

        if (dept == null) {
            Map<String, Object> res = new HashMap<>();
            res.put("msg", "해당 부서가 존재하지 않습니다.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
        }

        Emp emp = Emp.builder()
                .empno((Integer) req.get("empno"))
                .ename((String) req.get("ename"))
                .job((String) req.get("job"))
                .mgr((Integer) req.get("mgr"))
                .hiredate(LocalDate.parse((String) req.get("hiredate")))
                .sal(req.get("Sal") != null ? Double.valueOf(req.get("Sal").toString()) : null)
                .comm(req.get("comm") != null ? Double.valueOf(req.get("comm").toString()) : null)
                .dept(dept)
                .build();

        Emp saved = empRepository.save(emp);

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }


    
   }




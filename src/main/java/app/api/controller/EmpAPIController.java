package app.api.controller;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Dept;
import app.entity.Emp;
import app.repository.DeptRepository;
import app.repository.EmpRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@RestController
public class EmpAPIController {

    private final EmpRepository empRepository;
    private final DeptRepository deptRepository;

    @PostMapping("/api/emp")
    public Emp registerEmp(@RequestBody EmpRequest request) {
    	System.out.println(">>> registerEmp called"); 
        Dept dept = deptRepository.findById(request.getDeptno())
                .orElseThrow(() -> new RuntimeException("Department not found"));
        
        Emp emp = Emp.builder()
                .empno(request.getEmpno())
                .ename(request.getEname())
                .job(request.getJob())
                .mgr(request.getMgr())
                .hiredate(LocalDate.parse(request.getHiredate()))
                .sal(request.getSal())
                .comm(request.getComm())
                .dept(dept)
                .build();
                
        return empRepository.save(emp);
    }

    // 요청 DTO 클래스
    @Getter
    @Setter
    static class EmpRequest {
        private Integer empno;
        private String ename;
        private String job;
        private Integer mgr;
        private String hiredate;
        private Double sal;
        private Double comm;
        private Integer deptno;
    }

//  @PostMapping("/api/emp")
//  public Emp registerEmp(@RequestBody Emp emp) {
//      return empRepository.save(emp);
//  }

}

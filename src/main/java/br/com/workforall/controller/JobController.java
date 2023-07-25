package br.com.workforall.controller;

import br.com.workforall.exception.CompanyNotFoundException;
import br.com.workforall.model.Company;
import br.com.workforall.model.Job;
import br.com.workforall.model.dto.JobDto;
import br.com.workforall.repository.JobRepository;
import br.com.workforall.service.JobService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobService jobService;

    @GetMapping("/list")
    public List<Job> getJobs() {
        return jobRepository.findAll();
    }

    @GetMapping("/list/{cnpj}")
    public List<Job> getJobs(@PathVariable String cnpj) {
        return jobRepository.findByCnpj(cnpj);
    }

    @PostMapping("/register")
    public ResponseEntity<?> postCompany(@RequestBody @Valid JobDto jobDto) {
        try {
            Job job = jobService.processJobRegister(jobDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(job);
        }catch (CompanyNotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}

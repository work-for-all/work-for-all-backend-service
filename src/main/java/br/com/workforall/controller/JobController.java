package br.com.workforall.controller;

import br.com.workforall.exception.JobBadRequestException;
import br.com.workforall.model.Job;
import br.com.workforall.model.dto.JobDto;
import br.com.workforall.model.dto.JobUserDto;
import br.com.workforall.repository.JobRepository;
import br.com.workforall.service.JobService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{idJob}")
    public ResponseEntity<?> getJob(@PathVariable String idJob) {
        Optional<Job> jobOptional = jobRepository.findById(idJob);
        if(jobOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(jobOptional.get());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Vaga não cadastrada!");
    }

    @GetMapping("/list/{cnpj}")
    public List<Job> getJobs(@PathVariable String cnpj) {
        return jobRepository.findByCnpj(cnpj);
    }

    @PostMapping("/register")
    public ResponseEntity<?> postJob(@RequestBody @Valid JobDto jobDto) {
        try {
            Job job = jobService.processJobRegister(jobDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(job);
        }catch (JobBadRequestException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/update/{idVaga}")
    public ResponseEntity<?> putJob(@PathVariable String idVaga, @RequestBody @Valid JobDto jobDto) {
        try {
            Job job = jobService.processJobUpdate(idVaga, jobDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(job);
        }catch (JobBadRequestException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/update/status/{idVaga}")
    public ResponseEntity<?> putJobStatus(@PathVariable String idVaga) {
        try {
            Job job = jobService.processJobCloseStatus(idVaga);
            return ResponseEntity.status(HttpStatus.CREATED).body(job);
        }catch (JobBadRequestException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/update/candidate/{idVaga}")
    public ResponseEntity<?> putCandidateInJob(@PathVariable String idVaga, @RequestBody JobUserDto jobUserDto) {
        try {
            Job job = jobService.processJobAddCandidate(idVaga, jobUserDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(job);
        }catch (JobBadRequestException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}

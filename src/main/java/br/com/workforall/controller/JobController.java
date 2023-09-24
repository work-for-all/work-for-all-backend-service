package br.com.workforall.controller;

import br.com.workforall.exception.JobBadRequestException;
import br.com.workforall.model.Job;
import br.com.workforall.model.dto.JobDto;
import br.com.workforall.model.dto.JobUserDto;
import br.com.workforall.repository.JobRepository;
import br.com.workforall.service.JobService;
import br.com.workforall.service.UserService;
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
    private UserService userService;

    @Autowired
    private JobService jobService;

    @GetMapping("/quantity")
    public ResponseEntity<?> getQuantityJobs() {
        return ResponseEntity.status(HttpStatus.OK).body(jobService.findAllJobs().size());
    }

    @GetMapping("/quantity/womans")
    public ResponseEntity<?> getQuantityWomans() {
        return ResponseEntity.status(HttpStatus.OK).body(jobRepository.findByWoman(true).size());
    }

    @GetMapping("/quantity/fifty_years")
    public ResponseEntity<?> getQuantityFiftyYearsOrMore() {
        return ResponseEntity.status(HttpStatus.OK).body(jobRepository.findByFiftyYears(true).size());
    }

    @GetMapping("/quantity/deficient")
    public ResponseEntity<?> getDeficient() {
        return ResponseEntity.status(HttpStatus.OK).body(jobRepository.findByDeficient(true).size());
    }

    @GetMapping("/quantity/transsexual")
    public ResponseEntity<?> getTranssexual() {
        return ResponseEntity.status(HttpStatus.OK).body(jobRepository.findByTranssexual(true).size());
    }

    @GetMapping("/quantity/black_indigenous")
    public ResponseEntity<?> getBlackOrIndigenous() {
        return ResponseEntity.status(HttpStatus.OK).body(jobRepository.findByBlackIndigenous(true).size());
    }

    @GetMapping("/quantity/immigrants")
    public ResponseEntity<?> getImmigrants() {
        return ResponseEntity.status(HttpStatus.OK).body(jobRepository.findByImmigrants(true).size());
    }

    @GetMapping("/list")
    public ResponseEntity<?> getJobs() {
        return ResponseEntity.status(HttpStatus.OK).body(jobService.findAllJobs());
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

    @PutMapping("/update/{idJob}")
    public ResponseEntity<?> putJob(@PathVariable String idJob, @RequestBody @Valid JobDto jobDto) {
        try {
            Job job = jobService.processJobUpdate(idJob, jobDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(job);
        }catch (JobBadRequestException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/update/status/{idJob}")
    public ResponseEntity<?> putJobStatus(@PathVariable String idJob) {
        try {
            Job job = jobService.processJobCloseStatus(idJob);
            return ResponseEntity.status(HttpStatus.CREATED).body(job);
        }catch (JobBadRequestException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/update/candidate/{idJob}")
    public ResponseEntity<?> putCandidateInJob(@PathVariable String idJob, @RequestBody JobUserDto jobUserDto) {
        try {
            Job job = jobService.processJobAddCandidate(idJob, jobUserDto);
            userService.proccessUserAddJob(idJob, jobUserDto.getIdUser());
            return ResponseEntity.status(HttpStatus.CREATED).body(job);
        }catch (JobBadRequestException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
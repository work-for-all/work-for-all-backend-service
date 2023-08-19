package br.com.workforall.service;

import br.com.workforall.exception.JobBadRequestException;
import br.com.workforall.model.Job;
import br.com.workforall.model.dto.JobDto;
import br.com.workforall.model.dto.JobUserDto;
import br.com.workforall.repository.JobRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    public Job processJobRegister(JobDto jobDto) {
        Job job = modelMapper.map(jobDto, Job.class);
        job.setStatus(true);
        jobRepository.save(job);
        return job;
    }

    public Job processJobUpdate(String idVaga, JobDto jobDto) {
        Optional<Job> jobOptional = jobRepository.findById(idVaga);

        if(jobOptional.isPresent()){
            if(!jobOptional.get().isStatus()) throw new JobBadRequestException("Vaga Encerrada!");
        }
        Job job = modelMapper.map(jobDto, Job.class);
        job.setId(idVaga);
        job.setStatus(true);
        jobRepository.save(job);
        return job;
    }

    public Job processJobCloseStatus(String idVaga) {
        Optional<Job> job = jobRepository.findById(idVaga);

        if(job.isPresent()){
            job.get().setStatus(false);
            return jobRepository.save(job.get());
        }
        return null;
    }

    public Job processJobAddCandidate(String idVaga, JobUserDto jobUserDto) {
        Optional<Job> jobOptional = jobRepository.findById(idVaga);

        if(jobOptional.isPresent()){
            if(!jobOptional.get().isStatus()) throw new JobBadRequestException("Vaga encerrada!");
        }

        Job job = jobOptional.get();

        List<String> listCandidates;
        if(job.getCandidates() == null){
            listCandidates = new ArrayList<>();
        }else{
            listCandidates = job.getCandidates();

            for(String candidate : listCandidates){
                if(candidate.equalsIgnoreCase(jobUserDto.getIdUser())){
                    throw new JobBadRequestException("Candidatura já foi realizada!");
                }
            }
        }
        userService.findUser(jobUserDto.getIdUser());

        listCandidates.add(jobUserDto.getIdUser());
        job.setCandidates(listCandidates);

        jobRepository.save(job);
        return job;
    }

    public List<Job> findAllJobs(){
        return jobRepository.findAll();
    }
}

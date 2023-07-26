package br.com.workforall.service;

import br.com.workforall.exception.JobBadRequestException;
import br.com.workforall.model.Job;
import br.com.workforall.model.dto.JobDto;
import br.com.workforall.repository.JobRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Job processJobRegister(JobDto jobDto) {
        Job job = modelMapper.map(jobDto, Job.class);
        job.setStatus("OPEN");
        jobRepository.save(job);
        return job;
    }

    public Job processJobUpdate(String idVaga, JobDto jobDto) {
        Optional<Job> jobOptional = jobRepository.findById(idVaga);

        if(jobOptional.isPresent()){
            if(jobOptional.get().getStatus().equals("CLOSED")) throw new JobBadRequestException("Vaga Encerrada!");
        }
        Job job = modelMapper.map(jobDto, Job.class);
        job.setId(idVaga);
        job.setStatus("OPENED");
        jobRepository.save(job);
        return job;
    }

    public Job processJobCloseStatus(String idVaga) {
        Optional<Job> job = jobRepository.findById(idVaga);

        if(job.isPresent()){
            job.get().setStatus("CLOSED");
            return jobRepository.save(job.get());
        }
        return null;
    }
}

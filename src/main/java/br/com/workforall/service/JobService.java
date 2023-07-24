package br.com.workforall.service;

import br.com.workforall.model.Job;
import br.com.workforall.model.dto.JobDto;
import br.com.workforall.repository.JobRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Job processJobRegister(JobDto jobDto) {
        Job job = modelMapper.map(jobDto, Job.class);
        jobRepository.save(job);
        return job;
    }
}

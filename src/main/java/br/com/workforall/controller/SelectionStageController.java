package br.com.workforall.controller;


import br.com.workforall.exception.JobBadRequestException;
import br.com.workforall.model.SelectionStage;
import br.com.workforall.model.dto.SelectionStageDto;
import br.com.workforall.repository.SelectionStageRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/selection_stages")
public class SelectionStageController {

    @Autowired
    private SelectionStageRepository selectionStageRepository;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/register")
    public ResponseEntity<?> postSelectionStage(@RequestBody @Valid SelectionStageDto selectionStageDto) {
        try {
            SelectionStage selectionStage = new SelectionStage();
            selectionStage.setIdJob(selectionStageDto.getIdJob());
            selectionStage.setIdUser(selectionStageDto.getIdUser());
            selectionStage.setInterviewDescription(null);
            selectionStage.setTestDescription(null);
            selectionStage.setResultIndicator(null);
            selectionStage.setSelectionIndicator(null);

            return ResponseEntity.status(HttpStatus.CREATED).body(selectionStageRepository.save(selectionStage));
        }catch (JobBadRequestException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getSelectionStage(@RequestParam String idUser,
                                               @RequestParam String idJob) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(selectionStageRepository.findByIdUserAndIdJob(idUser, idJob));
        }catch (JobBadRequestException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}

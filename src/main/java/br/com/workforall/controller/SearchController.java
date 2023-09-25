package br.com.workforall.controller;

import br.com.workforall.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/searchs")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping
    public ResponseEntity<?> getJobsOrCompany(@RequestParam String title,
                                              @RequestParam Boolean fiftyYears,
                                              @RequestParam Boolean deficient,
                                              @RequestParam Boolean transsexual,
                                              @RequestParam Boolean blackIndigenous,
                                              @RequestParam Boolean neuro) {
        return ResponseEntity.status(HttpStatus.OK).body(
                searchService.findJob(
                        title, fiftyYears, deficient,
                        transsexual, blackIndigenous, neuro
                )
        );
    }
}
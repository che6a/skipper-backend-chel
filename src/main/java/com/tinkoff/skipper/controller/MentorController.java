package com.tinkoff.skipper.controller;

import com.tinkoff.skipper.dto.MentorDto;
import com.tinkoff.skipper.dto.MentorDataDto;
import com.tinkoff.skipper.dto.MentorProfileDto;
import com.tinkoff.skipper.service.MentorService;

import com.tinkoff.skipper.utils.SkipperResponseBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "api/mentor", produces = "application/json")
@RequiredArgsConstructor
@CrossOrigin(origins="http://localhost:8090")
public class MentorController {

    private final MentorService mentorService;

    @CrossOrigin
    @GetMapping(path = "{id}")
    public ResponseEntity<MentorProfileDto> getMentor(
            @PathVariable("id") Long id) {
        return SkipperResponseBuilder.buildResponse(
                HttpStatus.OK,
                mentorService.findById(id)
        );
    }

//    @PreAuthorize("hasAuthority('ROLE_USER')")
    @CrossOrigin
    @PostMapping
    public ResponseEntity<String> createMentor(
            @RequestBody MentorDto newMentor) {
        mentorService.save(newMentor);
        return SkipperResponseBuilder.buildResponse(
                HttpStatus.CREATED,
                "Информация о менторе успешно добавлена."
        );
    }

//    @PreAuthorize("hasAuthority('ROLE_USER')")
    @CrossOrigin
    @PutMapping(path = "{id}")
    public ResponseEntity<String> updateMentorProfile(
            @RequestBody MentorDataDto data,
            @PathVariable("id") Long id) {
        mentorService.update(id, data);
        return SkipperResponseBuilder.buildResponse(
                HttpStatus.OK,
                "Информация о менторе изменена."
        );
    }

//    @PreAuthorize("hasAuthority('ROLE_USER')")
    @CrossOrigin
    @DeleteMapping(path = "{id}")
    public ResponseEntity<String> deleteMentor(
            @PathVariable("id") Long id) {
        mentorService.delete(id);
        return SkipperResponseBuilder.buildResponse(
                HttpStatus.NO_CONTENT,
                "Информация о менторе успешно удалена."
        );
    }

}

package com.satya.CRM.controller;

import com.satya.CRM.entity.Note;
import com.satya.CRM.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/leads/{leadId}/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public List<Note> getNotes(@PathVariable Long leadId) {
        return noteService.getNotesByLeadId(leadId);
    }

    @PostMapping
    public Note createNote(@PathVariable Long leadId, @Valid @RequestBody Note note) {
        return noteService.createNote(leadId, note);
    }
}

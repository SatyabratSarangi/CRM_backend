package com.satya.CRM.service;

import com.satya.CRM.entity.CustomerLead;
import com.satya.CRM.entity.Note;
import com.satya.CRM.repository.CustomerLeadRepository;
import com.satya.CRM.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NoteService {


    private final NoteRepository noteRepository;


    private final CustomerLeadRepository customerLeadRepository;

    public NoteService(NoteRepository noteRepository, CustomerLeadRepository customerLeadRepository) {
        this.noteRepository = noteRepository;
        this.customerLeadRepository = customerLeadRepository;
    }

    public List<Note> getNotesByLeadId(Long leadId) {
        return noteRepository.findByLeadIdOrderByCreatedDateDesc(leadId);
    }

    public Note createNote(Long leadId, Note note) {
        CustomerLead lead = customerLeadRepository.findById(leadId)
                .orElseThrow(() -> new RuntimeException("Lead not found"));
        note.setLead(lead);
        return noteRepository.save(note);
    }
}

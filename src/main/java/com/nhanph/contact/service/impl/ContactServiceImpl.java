package com.nhanph.contact.service.impl;

import com.nhanph.contact.dto.ContactDto;
import com.nhanph.contact.dto.PageDto;
import com.nhanph.contact.entity.Contact;
import com.nhanph.contact.mapper.ContactMapper;
import com.nhanph.contact.repository.ContactRepository;
import com.nhanph.contact.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    private final ContactMapper contactMapper;

    @Autowired
    public ContactServiceImpl(ContactRepository contactRepository, ContactMapper contactMapper) {
        this.contactRepository = contactRepository;
        this.contactMapper = contactMapper;
    }

    @Override
    public void create(ContactDto contactDto) {
        contactRepository.save(contactMapper.toEntity(contactDto));
    }

    @Override
    public ContactDto getById(Long id) {
        ContactDto contactDto = new ContactDto();
        Contact contact = contactRepository.findById(id).orElse(null);

        if (contact != null) {
            contactDto = contactMapper.toDto(contact);
        }

        return contactDto;
    }

    @Override
    public PageDto search(String search, Integer page, Integer size, String sortBy, String sortType) {

        Sort sort = null;
        if (sortType.equalsIgnoreCase("asc")) {
            sort = Sort.by(sortBy).ascending();
        } else {
            sort = Sort.by(sortBy).descending();
        }

        Page<Contact> contacts = contactRepository.findByNameLike(search, PageRequest.of(page, size, sort));

        return PageDto.builder()
                .content(contacts.getContent())
                .totalElements(contacts.getTotalElements())
                .number(contacts.getNumber())
                .numberOfElements(contacts.getNumberOfElements())
                .build();
    }

    @Override
    public void update(Long id, ContactDto contactDto) {
        Contact contact = contactRepository.findById(id).orElse(null);
        if (contact != null) {
            contact = contactMapper.partialUpdate(contactDto, contact);
            contactRepository.save(contact);
        }
    }

    @Override
    public void delete(Long id) {
        if (contactRepository.existsById(id)) {
            contactRepository.deleteById(id);
        }
    }
}

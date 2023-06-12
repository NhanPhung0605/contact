package com.nhanph.contact.service;

import com.nhanph.contact.dto.ContactDto;
import com.nhanph.contact.dto.PageDto;

public interface ContactService {

    void create(ContactDto contactDto);

    ContactDto getById(Long id);

    PageDto search(String search, Integer page, Integer size, String sortBy, String sortType);

    void update(Long id, ContactDto contactDto);

    void delete(Long id);
}

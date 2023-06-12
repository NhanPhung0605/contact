package com.nhanph.contact.mapper;

import com.nhanph.contact.dto.ContactDto;
import com.nhanph.contact.entity.Contact;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ContactMapper {


    Contact toEntity(ContactDto contactDto);

    ContactDto toDto(Contact contact);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Contact partialUpdate(ContactDto contactDto, @MappingTarget Contact contact);
}

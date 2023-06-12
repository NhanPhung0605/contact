package com.nhanph.contact.controller;

import com.nhanph.contact.dto.ContactDto;
import com.nhanph.contact.dto.PageDto;
import com.nhanph.contact.service.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
@Tag(name = "Contact", description = "The Contact Service API with description tag annotation")
@RequestMapping("/contact")
public class ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @Operation(summary = "Create Contact")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Create Contact Success", content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", description = "Create Contact Fail", content = @Content) })
    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody ContactDto contactDto) {
        contactService.create(contactDto);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get Contact")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get Contact Success", content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", description = "Get Contact Fail", content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<ContactDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(contactService.getById(id));
    }

    @Operation(summary = "Search Contact")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Search Contact Success", content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", description = "Search Contact Fail", content = @Content) })
    @GetMapping
    public ResponseEntity<PageDto> search(@RequestParam("search") String search,
                                          @RequestParam("page") Integer page,
                                          @RequestParam("size") Integer size,
                                          @RequestParam("sortBy") String sortBy,
                                          @RequestParam("sortType") String sortType) {
        return ResponseEntity.ok(contactService.search(search, page, size, sortBy, sortType));
    }

    @Operation(summary = "Update Contact")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update Contact Success", content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", description = "Update Contact Fail", content = @Content) })
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id,
                                       @Valid @RequestBody ContactDto contactDto) {
        contactService.update(id, contactDto);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete Contact")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete Contact Success", content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", description = "Delete Contact Fail", content = @Content) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        contactService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

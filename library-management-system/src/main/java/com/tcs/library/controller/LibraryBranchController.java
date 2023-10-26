package com.tcs.library.controller;

import com.tcs.library.model.LibraryBranch;
import com.tcs.library.service.LibraryBranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LibraryBranchController {
    @Autowired
    private LibraryBranchService libraryBranchService;

    @GetMapping("/libraryBranches")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<LibraryBranch> getAllLibraryBranches() {
        return libraryBranchService.fetchAllLibraryBranches();
    }

    @GetMapping("/libraryBranches/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public LibraryBranch getLibraryBranchById(@PathVariable int id) {
        return libraryBranchService.fetchLibraryBranchById(id);
    }

    @PostMapping(value = "/add/libraryBranches", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public LibraryBranch addLibraryBranch(@RequestBody LibraryBranch libraryBranch) {
        return libraryBranchService.addLibraryBranch(libraryBranch);
    }

    @PutMapping(value = "/update/libraryBranches/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public LibraryBranch updateLibraryBranch(@PathVariable int id, @RequestBody LibraryBranch libraryBranch) {
        return libraryBranchService.updateLibraryBranch(id, libraryBranch);
    }

    @DeleteMapping("/delete/libraryBranches/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void deleteLibraryBranchById(@PathVariable int id) {
        libraryBranchService.removeLibraryBranchById(id);
    }
}

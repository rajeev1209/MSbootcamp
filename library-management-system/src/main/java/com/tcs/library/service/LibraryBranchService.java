package com.tcs.library.service;

import com.tcs.library.model.LibraryBranch;
import com.tcs.library.repository.LibraryBranchRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class LibraryBranchService {

    @Autowired
    private LibraryBranchRepository libraryBranchRepository;

    public List<LibraryBranch> fetchAllLibraryBranches() {
        return libraryBranchRepository.findAll();
    }

    public LibraryBranch fetchLibraryBranchById(int id) {
        return libraryBranchRepository.findById(id).orElse(null);
    }

    public LibraryBranch addLibraryBranch(LibraryBranch libraryBranch) {
        return libraryBranchRepository.save(libraryBranch);
    }

    public LibraryBranch updateLibraryBranch(int id, LibraryBranch libraryBranch) {
        Optional<LibraryBranch> libraryBranchOpt = libraryBranchRepository.findById(id);
        if (libraryBranchOpt.isPresent()) {
            libraryBranch.setId(libraryBranchOpt.get().getId());
            libraryBranchRepository.save(libraryBranch);
        } else {
            log.info("LibraryBranch doesn't exist" + libraryBranch.getLibraryName());
        }
        return libraryBranch;
    }

    public void removeLibraryBranchById(int id) {
        libraryBranchRepository.deleteById(id);
    }
}

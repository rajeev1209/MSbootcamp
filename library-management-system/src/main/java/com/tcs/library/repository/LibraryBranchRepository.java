package com.tcs.library.repository;

import com.tcs.library.model.LibraryBranch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryBranchRepository extends JpaRepository<LibraryBranch,Integer> {
}

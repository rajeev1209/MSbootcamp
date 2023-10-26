package com.tcs.library.service;

import com.tcs.library.model.Borrower;
import com.tcs.library.repository.BorrowerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BorrowerService {

    @Autowired
    private BorrowerRepository borrowerRepository;

    public List<Borrower> fetchAllBorrowers() {
        return borrowerRepository.findAll();
    }

    public Borrower fetchBorrowerById(int id) {
        return borrowerRepository.findById(id).orElse(null);
    }

    public Borrower addBorrower(Borrower borrower) {
        return borrowerRepository.save(borrower);
    }

    public Borrower updateBorrower(int id, Borrower borrower) {
        Optional<Borrower> borrowerOpt = borrowerRepository.findById(id);
        if (borrowerOpt.isPresent()) {
            borrower.setId(borrowerOpt.get().getId());
            borrowerRepository.save(borrower);
        } else {
            log.info("Borrower doesn't exist" + borrower.getFirst_name());
        }
        return borrower;
    }

    public void removeBorrowerById(int id) {
        Optional<Borrower> borrowerOpt = borrowerRepository.findById(id);
        if (borrowerOpt.isPresent()) {
            borrowerRepository.deleteById(id);
        } else {
            log.info("Borrower doesn't exist so can't delete");
        }

    }
}

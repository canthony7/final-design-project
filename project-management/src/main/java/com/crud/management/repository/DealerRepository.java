package com.crud.management.repository;

import com.crud.management.pojo.Dealer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DealerRepository extends PagingAndSortingRepository<Dealer, Long> {

    List<Dealer> findByEnable(Integer enable);

    Dealer findByDealerNumber(String dealerNumber);

    Page<Dealer> findDealerByEnable(Pageable pageable, Integer enable);

}

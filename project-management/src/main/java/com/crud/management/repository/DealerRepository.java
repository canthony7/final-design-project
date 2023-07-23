package com.crud.management.repository;

import com.crud.management.pojo.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DealerRepository extends JpaRepository<Dealer, Long> {

    List<Dealer> findByEnable(Integer enable);

    Dealer findByDealerNumber(String dealerNumber);

}

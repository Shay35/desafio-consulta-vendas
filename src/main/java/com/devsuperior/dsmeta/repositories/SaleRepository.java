package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT obj FROM Sale obj WHERE obj.date BETWEEN :min AND :max AND LOWER(obj.seller.name) " +
            "LIKE CONCAT('%', LOWER(:name), '%')")
    public Page<Sale> searchSales(@Param("name") String name, @Param("min") LocalDate min, @Param("max") LocalDate max, Pageable pageable);

    @Query("SELECT new com.devsuperior.dsmeta.dto.SaleSummaryDTO(obj.seller.name, SUM(obj.amount)) FROM Sale obj WHERE obj.date BETWEEN :min AND :max GROUP BY obj.seller.name")
    public List<SaleSummaryDTO> salesSummary(@Param("min") LocalDate min, @Param("max") LocalDate max);
}

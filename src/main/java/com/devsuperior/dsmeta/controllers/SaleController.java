package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SaleDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

import java.util.List;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<SaleDTO>> getReport(@RequestParam(required = false) String minDate, @RequestParam(required = false)
	String maxDate, @RequestParam(required = false) String name, Pageable pageable) {
		Page<SaleDTO> sales = service.searchSales(minDate, maxDate, name, pageable);
		return ResponseEntity.ok(sales);
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<List<SaleSummaryDTO> > getSummary(@RequestParam(required = false) String minDate, @RequestParam(required = false) String maxDate) {
        List<SaleSummaryDTO> result = service.salesSummary(minDate, maxDate);
		return ResponseEntity.ok(result);
	}
}

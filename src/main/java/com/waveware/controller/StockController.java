package com.waveware.controller;

import com.waveware.dto.StockDTO;
import com.waveware.dto.StockSymbolDTO;
import com.waveware.mapper.StockMapper;
import javafx.util.converter.LocalDateStringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@RestController
public class StockController
{
	@Autowired
	private StockMapper mapper;

	@GetMapping("/stock/{prefix}/{symbol}")
	public ResponseEntity<List<StockDTO>> findByStock(@PathVariable("prefix") String prefix, @PathVariable("symbol") String symbol, @RequestParam(required = true) @DateTimeFormat(pattern = "yyyyMMdd") LocalDate startDate, @DateTimeFormat(pattern = "yyyyMMdd") LocalDate endDate)
	{
		String fullSymbol = String.format("%s_%s", prefix, symbol);
		return ResponseEntity.ok(mapper.selectStock(fullSymbol, startDate.toString(), endDate.toString()));
	}

	@GetMapping("/stock/calendar")
	public ResponseEntity<Map<Long, List<StockDTO>>> finyByCalendarAllStock(@RequestParam(required = true) @DateTimeFormat(pattern = "yyyyMMdd") LocalDate date)
	{
		List<StockDTO> list = mapper.selectMonthOfAllStock(String.format("%s%s", date.getYear(), date.getMonthValue()));

		Map<Long, List<StockDTO>> map = new HashMap<>();
		Calendar cal = Calendar.getInstance();
		cal.set(date.getYear(), date.getMonthValue()-1, 1, 0, 0, 0);
		cal.set(Calendar.MILLISECOND,0);
		List<Long> dateList = new ArrayList<>();

		while (cal.get(Calendar.MONTH) == date.getMonthValue()-1)
		{
			dateList.add(cal.getTime().getTime());
			cal.add(Calendar.DAY_OF_MONTH, +1);
		}

		for (Long d : dateList)
		{
			List<StockDTO> itemList = map.computeIfAbsent(d, (obj) -> new ArrayList<StockDTO>());
			Iterator<StockDTO> it = list.iterator();
			while (it.hasNext())
			{
				StockDTO stock = it.next();
				if (d  == stock.getDate().getTime())
				{
					itemList.add(stock);
					it.remove();
				}
			}
		}

		return ResponseEntity.ok(map);
	}

	@GetMapping("/stock/list")
	public ResponseEntity<List<StockSymbolDTO>> getSymbolList()
	{
		return ResponseEntity.ok(StockSymbolDTO.getList());
	}
}
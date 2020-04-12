package com.example.demo;

import java.time.Duration;
import java.util.Date;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

@Service
class StockTransactionService {
    Flux<StockTransaction> getStockTransactions() {
        Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));
        interval.subscribe();
        Flux<StockTransaction> stockTransactionFlux = Flux.fromStream(Stream.generate(() -> new StockTransaction("User", 
        		new Stock("TCS", 1.5f), new Date())));
        return Flux.zip(interval, stockTransactionFlux).map(Tuple2::getT2);
    }
    
}

package com.github.iluwa.transportscheduleaggregator;

import com.github.iluwa.transportscheduleaggregator.service.DictionariesService;
import com.github.iluwa.transportscheduleaggregator.service.RouteService;
import com.github.iluwa.transportscheduleaggregator.yandex.*;
import com.github.iluwa.transportscheduleaggregator.yandex.search.SearchRequest;
import com.github.iluwa.transportscheduleaggregator.yandex.search.responsestructure.SearchResponse;
import com.github.iluwa.transportscheduleaggregator.yandex.stationslist.responsestructure.StationsListResponse;
import com.github.iluwa.transportscheduleaggregator.yandex.stationslist.StationsListRequest;
import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TransportScheduleAggregatorApplication implements CommandLineRunner {
	@Autowired
	DictionariesService dictionariesService;

	@Autowired
	RouteService routeService;

	public static void main(String[] args) {
		SpringApplication.run(TransportScheduleAggregatorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// stationsListRequest debug

//		StationsListRequest.Builder builder =
//				new StationsListRequest.Builder(ApiKeyStorage.YANDEX_API_KEY);
//		ApiRequest stationsListReq = builder.build();
//		HttpRequest resp = stationsListReq.sendGetRequest();
//
//		if (resp.code() == 200) {
//			dictionariesService.clearDictionaries();
//			Gson gson = new Gson();
//			StationsListResponse response = gson.fromJson(resp.body(), StationsListResponse.class);
//			dictionariesService.stationsListResponseToEntity(response);
//		}


		// Search request debug

//		SearchRequest.Builder builder =
//				new SearchRequest.Builder(ApiKeyStorage.YANDEX_API_KEY, "s2000001", "c192");
//
//		builder.setDate("2019-04-19");
//		SearchRequest req = builder.build();
//		HttpRequest resp = req.sendGetRequest();
//		if (resp.code() == 200) {
//			Gson gson = new Gson();
//			SearchResponse response = gson.fromJson(resp.body(), SearchResponse.class);
//			routeService.searchResponseToEntity(response);
//		}
	}
}

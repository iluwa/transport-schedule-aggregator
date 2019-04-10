package com.github.iluwa.transportscheduleaggregator;

import com.github.iluwa.transportscheduleaggregator.db.BaseDao;
import com.github.iluwa.transportscheduleaggregator.service.DictionariesService;
import com.github.iluwa.transportscheduleaggregator.yandex.ApiCommands;
import com.github.iluwa.transportscheduleaggregator.yandex.ApiCommandsImpl;
import com.github.iluwa.transportscheduleaggregator.yandex.ApiKeyStorage;
import com.github.iluwa.transportscheduleaggregator.yandex.response.stationsliststructure.CountriesContainer;
import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class TransportScheduleAggregatorApplication implements CommandLineRunner {
	@Autowired
	DictionariesService dictionariesService;

	public static void main(String[] args) {
		SpringApplication.run(TransportScheduleAggregatorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ApiCommands apiCommands = new ApiCommandsImpl(ApiKeyStorage.YANDEX_API_KEY);
		Map<String, String> params = new HashMap<>();
		HttpRequest resp = apiCommands.stationsList(new HashMap<>());

		if (resp.code() == 200) {
			dictionariesService.clearDictionaries();
			Gson gson = new Gson();
			CountriesContainer countriesContainer = gson.fromJson(resp.body(), CountriesContainer.class);
			dictionariesService.countriesToEntity(countriesContainer);
		}
	}
}

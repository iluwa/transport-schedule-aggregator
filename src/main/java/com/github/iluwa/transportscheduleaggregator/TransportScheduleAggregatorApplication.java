package com.github.iluwa.transportscheduleaggregator;

import com.github.iluwa.transportscheduleaggregator.service.DictionariesService;
import com.github.iluwa.transportscheduleaggregator.service.RouteService;
import com.github.iluwa.transportscheduleaggregator.userinteractionhelper.commandhandlers.FindStationCodeHandler;
import com.github.iluwa.transportscheduleaggregator.userinteractionhelper.exceptions.CodeNotFoundException;
import com.github.iluwa.transportscheduleaggregator.userinteractionhelper.Commands;
import com.github.iluwa.transportscheduleaggregator.userinteractionhelper.commandhandlers.RoutesByDateHandler;
import com.github.iluwa.transportscheduleaggregator.userinteractionhelper.exceptions.HttpException;
import com.github.iluwa.transportscheduleaggregator.yandex.ApiKeyStorage;
import com.github.iluwa.transportscheduleaggregator.yandex.ApiRequest;
import com.github.iluwa.transportscheduleaggregator.yandex.stationslist.StationsListRequest;
import com.github.iluwa.transportscheduleaggregator.yandex.stationslist.responsestructure.StationsListResponse;
import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.format.DateTimeParseException;
import java.util.*;

@SpringBootApplication
public class TransportScheduleAggregatorApplication implements CommandLineRunner {
	@Autowired
	private DictionariesService dictionariesService;

	@Autowired
	private RouteService routeService;

	private final static Logger LOGGER = LoggerFactory.getLogger(TransportScheduleAggregatorApplication.class);

	private final static String FORMAT_ERROR = "Not valid command format";
	private static final Map<String, Commands> COMMANDS_MAP;
	static {
		Map<String, Commands> m = new HashMap<>();
		m.put("q", Commands.QUIT);
		m.put("quit", Commands.QUIT);
		m.put("intervalPriceByUidHandler", Commands.INTREVAL_PRICE_BY_UID);
		m.put("routesByDate", Commands.ROUTES_BY_DATE);
		m.put("findStationCode", Commands.FIND_STATION_CODE);
		COMMANDS_MAP = Collections.unmodifiableMap(m);
	}

	public static void main(String[] args) {
		SpringApplication.run(TransportScheduleAggregatorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println("Preparation... Please, wait a bit...");
		if (!dictionariesService.isDictionaryLoaded()) {
			LOGGER.info("Loading dictionary, get request StationsList");
			StationsListRequest.Builder builder =
					new StationsListRequest.Builder(ApiKeyStorage.YANDEX_API_KEY);
			ApiRequest stationsListReq = builder.build();
			HttpRequest resp = stationsListReq.sendGetRequest();

			LOGGER.info("Http response code: " + resp.code());
			if (resp.code() == 200) {
				dictionariesService.clearDictionary();
				Gson gson = new Gson();
				StationsListResponse response = gson.fromJson(resp.body(), StationsListResponse.class);
				dictionariesService.stationsListResponseToEntity(response);
			}
		}
		routeService.deleteOutdatedRoutes();

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter command");
		System.out.println("Available commands: " + COMMANDS_MAP.keySet());

		boolean isInterrupted = false;
		while (!isInterrupted) {
			String inputString = sc.nextLine();
			LOGGER.info("User's input: " + inputString);
			StringTokenizer st = new StringTokenizer(inputString, " ");
			String command = st.hasMoreTokens() ? st.nextToken() : "";

			try {
				switch (COMMANDS_MAP.getOrDefault(command, Commands.EMPTY)) {
					case QUIT:
						LOGGER.trace("Handle quit command");
						isInterrupted = true;
						break;
					case ROUTES_BY_DATE:
						LOGGER.trace("Handle routes by date command");
						if (st.countTokens() != 3) {
							System.out.println(FORMAT_ERROR);
							System.out.println(Commands.ROUTES_BY_DATE.getExample());
						}
						new RoutesByDateHandler(st.nextToken(), st.nextToken(), st.nextToken(),
								dictionariesService, routeService).handle();
						break;
					case INTREVAL_PRICE_BY_UID:
						LOGGER.trace("Handle interval price by uid command");
						if (st.countTokens() != 4) {
							System.out.println(FORMAT_ERROR);
							System.out.println(Commands.INTREVAL_PRICE_BY_UID.getExample());
							break;
						}
						break;
					case FIND_STATION_CODE:
						LOGGER.trace("Handle find station code command");
						if (st.countTokens() != 1) {
							System.out.println(FORMAT_ERROR);
							System.out.println(Commands.FIND_STATION_CODE.getExample());
						}
						new FindStationCodeHandler(st.nextToken(), dictionariesService).handle();
						break;
					default:
						System.out.println("Unknown command");
						break;
				}
			} catch (DateTimeParseException e) {
				System.out.println("Incorrect date format. Correct: DD.MM.YYYY");
			} catch (CodeNotFoundException e) {
				System.out.println("Code for your stations was not found");
			} catch (HttpException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}

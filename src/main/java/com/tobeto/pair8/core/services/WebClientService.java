
package com.tobeto.pair8.core.services;

import com.tobeto.pair8.core.utilities.mappers.services.ModelMapperService;
import com.tobeto.pair8.services.dtos.city.GetListCityResponse;
import com.tobeto.pair8.services.dtos.district.GetListDistrictResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WebClientService {
    private final WebClient webClient;
    private final ModelMapperService modelMapper;

    public Mono<List<GetListCityResponse>> CityAllRequest() {

        String publicApiUrl = "https://turkiyeapi.dev/api/v1/provinces?fields=name,districts";
        return webClient.get()
                .uri(publicApiUrl)
                .retrieve()
                .bodyToMono(Map.class)
                .map(responseMap -> ((List<Map<String, Object>>) responseMap.get("data")).stream()
                        .map(cityData -> {
                            String cityName = (String) cityData.get("name");
                            List<Map<String, Object>> districtDataList = (List<Map<String, Object>>) cityData.get("districts");
                            List<GetListDistrictResponse> districts = districtDataList.stream()
                                    //.limit(5) // Her il için sadece belirtilen limit kadar ilçe al
                                    .map(districtData -> modelMapper.forResponse().map(districtData, GetListDistrictResponse.class))
                                    .collect(Collectors.toList());

                            return new GetListCityResponse(cityName, districts);
                        })
                        .collect(Collectors.toList())
                );
    }
}
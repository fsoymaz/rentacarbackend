
package com.tobeto.pair8.services.concretes;
import com.tobeto.pair8.core.services.WebClientService;
import com.tobeto.pair8.core.utilities.mappers.services.ModelMapperService;
import com.tobeto.pair8.entities.concretes.City;
import com.tobeto.pair8.entities.concretes.District;
import com.tobeto.pair8.repositories.CityRepository;
import com.tobeto.pair8.repositories.DistrictRepository;
import com.tobeto.pair8.services.abstracts.DistrictService;
import com.tobeto.pair8.services.dtos.city.GetListCityResponse;
import com.tobeto.pair8.services.dtos.district.GetListDistrictResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@AllArgsConstructor
public class DistrictManager implements DistrictService {
    private final WebClientService webClientService;
    private final DistrictRepository districtRepository;
    private final ModelMapperService mapperService;
    private final CityRepository cityRepository;
    @Override // TEST APİ
    public void create() {
        /*
        Mono<List<GetListCityResponse>> cityDataMono = webClientService.CityAllRequest();

        // Alınan verileri işle ve veritabanına kaydet
        cityDataMono.subscribe(cityDataList -> {
            for (GetListCityResponse cityResponse : cityDataList) {
                City cityEntity = mapperService.forRequest().map(cityResponse, City.class);

                // İlgili şehir bilgisi veritabanına kaydediliyor
                cityEntity = cityRepository.save(cityEntity);

                // Şehre bağlı ilçeleri kaydet
                for (GetListDistrictResponse districtResponse : cityResponse.getDistricts()) {
                    District districtEntity = mapperService.forRequest().map(districtResponse, District.class);
                    districtEntity.setCity(cityEntity);
                    districtRepository.save(districtEntity);
                }
            }
        });
         */
    }
}

package com.tobeto.pair8.services.concretes;

import com.tobeto.pair8.core.utilities.mappers.services.ModelMapperService;
import com.tobeto.pair8.entities.concretes.Brand;
import com.tobeto.pair8.repositories.BrandRepository;
import com.tobeto.pair8.rules.brand.BrandBusinessRulesService;
import com.tobeto.pair8.services.dtos.brand.requests.AddBrandRequest;
import com.tobeto.pair8.services.dtos.brand.requests.UpdateBrandRequest;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

class BrandManagerTest {
    private BrandManager brandManager;
    @Mock
    private BrandRepository brandRepository;
    @Mock
    private ModelMapperService modelMapperService;
    @Mock
    private BrandBusinessRulesService brandBusinessRulesService;

    BrandManagerTest() {
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.brandManager = new BrandManager(this.brandRepository, this.modelMapperService, this.brandBusinessRulesService);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void nameWithTwoLettersShouldThrowException() {
        AddBrandRequest addBrandRequest = new AddBrandRequest();
        addBrandRequest.setName("aaa");
        Assertions.assertThrows(RuntimeException.class, () -> {
            this.brandManager.add(addBrandRequest);
        });
    }

    @Test
    void brandWithSameNameShouldNotExist() {
        AddBrandRequest addBrandRequest = new AddBrandRequest();
        addBrandRequest.setName("Mercedes");
        Mockito.when(this.brandRepository.findByName("Mercedes")).thenReturn(Optional.of(new Brand("Mercedes")));
        Assertions.assertThrows(RuntimeException.class, () -> {
            this.brandManager.add(addBrandRequest);
        });
    }

    @Test
    void successful() {
        AddBrandRequest addBrandRequest = new AddBrandRequest();
        addBrandRequest.setName("Bugatti");
        ModelMapper mockModelMapper = new ModelMapper();
        Mockito.when(this.modelMapperService.forRequest()).thenReturn(mockModelMapper);
        this.brandManager.add(addBrandRequest);
    }

    @Test
    void brandNameShouldUpdate() {
        UpdateBrandRequest updateBrandRequest = new UpdateBrandRequest();
        updateBrandRequest.setId(1);
        updateBrandRequest.setName("Renault");
        Brand existingBrand = new Brand();
        existingBrand.setId(1);
        Mockito.when(this.brandRepository.findById(updateBrandRequest.getId())).thenReturn(Optional.of(existingBrand));
        ModelMapper mockModelMapper = new ModelMapper();
        Mockito.when(this.modelMapperService.forRequest()).thenReturn(mockModelMapper);
        this.brandManager.update(updateBrandRequest);
    }
}

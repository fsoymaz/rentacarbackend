package com.tobeto.pair8.services.dtos.corporateCustomer.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UpdateCorporateCustomerRequest {
@NotNull(message = "ID boş olamaz")
    private int id;
    @NotBlank(message = "Şirket adı boş olamaz")
    private String companyName;

    @NotBlank(message = "Vergi numarası boş olamaz")
    @Pattern(regexp = "[0-9]{10}", message = "Geçerli bir vergi numarası giriniz (10 haneli)")
    private String taxNo;

    @NotNull(message = "Kullanıcı ID boş olamaz")
    private Integer userId;
}

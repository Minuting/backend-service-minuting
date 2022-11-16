package net.huray.backend.minuting.web.home.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import net.huray.backend.minuting.entity.Company;

@Builder
@Getter
@AllArgsConstructor
public class CompanyRes {

    private Long id;
    private String name;
    private String ceo;
    private String telNumber;
    private String address;

    public static CompanyRes from(Company company){
        return CompanyRes.builder()
            .id(company.getId())
            .name(company.getName())
            .ceo(company.getCeo())
            .telNumber(company.getTelNumber())
            .address(company.getAddress())
            .build();
    }

}

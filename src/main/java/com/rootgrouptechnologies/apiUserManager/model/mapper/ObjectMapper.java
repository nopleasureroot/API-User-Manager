package com.rootgrouptechnologies.apiUserManager.model.mapper;

import com.rootgrouptechnologies.apiUserManager.entity.Billing;
import com.rootgrouptechnologies.apiUserManager.entity.Licence;
import com.rootgrouptechnologies.apiUserManager.entity.LicenceType;
import com.rootgrouptechnologies.apiUserManager.entity.User;
import com.rootgrouptechnologies.apiUserManager.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ObjectMapper {
    ObjectMapper INSTANCE = Mappers.getMapper(ObjectMapper.class);

    @Mapping(source = "billing", target = "billingDTO", qualifiedByName = "toBillingDTO")
    @Mapping(source = "licence", target = "licenceDTO", qualifiedByName = "toLicenceDTO")
    @Mapping(source = "user", target = "userDTO")
    @Mapping(source = "licenceType", target = "licenceTypeDTO", qualifiedByName = "toLicenceDTO")
    ResultDTO toResultDTO(User user, Licence licence, LicenceType licenceType, Billing billing);

    UserDTO toUserDTO(User user);

    @Named("toLicenceDTO")
    @Mapping(source = "licenceType.renewPrice", target = "renewalPrice")
    @Mapping(source = "licenceType.majorRoleName", target = "role")
    LicenceTypeDTO toLicenceTypeDTO(LicenceType licenceType);

    @Named("toLicenceDTO")
    default LicenceDTO toLicenceDTO(Licence licence) {
        String key;
        Boolean bind;
        String renewal;

        if (licence == null) {
            return null;
        }

        if (licence.getRenewalDate() != null) {
            key = licence.getIdentifier();
            bind = licence.getActivated();
            renewal = licence.getRenewalDate().split(" ")[0];
        } else {
            key = licence.getIdentifier();
            bind = false;
            renewal = null;
        }

        return new LicenceDTO(key, bind, renewal);
    }

    @Named("toBillingDTO")
    default BillingDTO toBillingDTO(Billing billing) {
        String cartDate;
        Integer cartEnding;
        String paymentId;
        boolean isBind;

        if (billing == null) {
            return null;
        }

        if (billing.getCardDate() != null && billing.getCartNumberEnding() != null) {
            cartDate = billing.getCardDate();
            cartEnding = billing.getCartNumberEnding();
            paymentId = billing.getPaymentId();
            isBind = true;
        } else if (billing.getPaymentId() != null){
            cartDate = "Cart not found";
            cartEnding = null;
            paymentId = billing.getPaymentId();
            isBind = false;
        } else {
            cartDate = "Cart not found";
            cartEnding = null;
            paymentId = null;
            isBind = false;
        }

        return new BillingDTO(cartDate, cartEnding, paymentId, isBind);
    }
}

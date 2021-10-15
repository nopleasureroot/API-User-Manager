package com.rootgrouptechnologies.apiUserManager.model.mapper;

import com.rootgrouptechnologies.apiUserManager.entity.Billing;
import com.rootgrouptechnologies.apiUserManager.entity.Licence;
import com.rootgrouptechnologies.apiUserManager.entity.LicenceType;
import com.rootgrouptechnologies.apiUserManager.entity.User;
import com.rootgrouptechnologies.apiUserManager.model.BillingDTO;
import com.rootgrouptechnologies.apiUserManager.model.LicenceDTO;
import com.rootgrouptechnologies.apiUserManager.model.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Date;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "user.id", target = "id")
    @Mapping(source = "user.discordEmail", target = "discordEmail")
    @Mapping(source = "user.discordUsername", target = "discordUsername")
    @Mapping(source = "user.creationDate", target = "creationDate")
    @Mapping(source = "licenceType.renewPrice", target = "licenceTypeDTO.renewalPrice")
    @Mapping(source = "licenceType.majorRoleName", target = "licenceTypeDTO.role")
    @Mapping(source = "billing", target = "billingDTO", qualifiedByName = "toBillingDTO")
    @Mapping(source = "licence", target = "licenceDTO", qualifiedByName = "toLicenceDTO")
    UserDTO toUserDTO(User user, Licence licence, LicenceType licenceType, Billing billing);

    @Named("toLicenceDTO")
    default LicenceDTO toLicenceDTO(Licence licence) {
        String key;
        Boolean bind;
        String renewal;

        if (licence.getRenewalDate() != null) {
            key = licence.getIdentifier();
            bind = licence.getActivated();
            renewal = licence.getRenewalDate().toString().split(" ")[0];
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

package com.rootgrouptechnologies.apiUserManager.model.mapper;

import com.rootgrouptechnologies.apiUserManager.entity.*;
import com.rootgrouptechnologies.apiUserManager.model.DTO.*;
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
    @Mapping(source = "licenceType", target = "licenceTypeDTO", qualifiedByName = "toLicenceTypeDTO")
    @Mapping(source = "payment", target = "paymentDTO")
    ResultUserDTO toResultDTO(User user, Licence licence, LicenceType licenceType, Billing billing, Payment payment);

    UserDTO toUserDTO(User user);

    PaymentDTO toPaymentDTO(Payment payment);

    @Mapping(source = "inventory.password", target = "password")
    @Mapping(source = "inventory.quantity", target = "quantity")
    @Mapping(source = "inventory.isActive", target = "isActive")
    @Mapping(source = "inventory.creationDate", target = "creationDate")
    DropDTO toDropDTO(Inventory inventory);

    @Named("toLicenceTypeDTO")
    @Mapping(source = "licenceType.renewPrice", target = "renewalPrice")
    @Mapping(source = "licenceType.majorRoleName", target = "role")
    LicenceTypeDTO toLicenceTypeDTO(LicenceType licenceType);

    @Named("toLicenceDTO")
    default LicenceDTO toLicenceDTO(Licence licence) {
        Integer id;
        String key;
        Boolean bind;
        String renewal;

        if (licence == null) {
            return null;
        }

        if (licence.getRenewalDate() != null) {
            id = licence.getId();
            key = licence.getIdentifier();
            bind = licence.getActivated();
            renewal = licence.getRenewalDate().split(" ")[0];
        } else {
            id = licence.getId();
            key = licence.getIdentifier();
            bind = false;
            renewal = null;
        }

        return new LicenceDTO(id, key, bind, renewal);
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

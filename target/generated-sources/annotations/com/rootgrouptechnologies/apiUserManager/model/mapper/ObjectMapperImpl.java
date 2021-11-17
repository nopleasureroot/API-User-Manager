package com.rootgrouptechnologies.apiUserManager.model.mapper;

import com.rootgrouptechnologies.apiUserManager.entity.Billing;
import com.rootgrouptechnologies.apiUserManager.entity.Licence;
import com.rootgrouptechnologies.apiUserManager.entity.LicenceType;
import com.rootgrouptechnologies.apiUserManager.entity.Payment;
import com.rootgrouptechnologies.apiUserManager.entity.User;
import com.rootgrouptechnologies.apiUserManager.model.DTO.BillingDTO;
import com.rootgrouptechnologies.apiUserManager.model.DTO.LicenceDTO;
import com.rootgrouptechnologies.apiUserManager.model.DTO.LicenceTypeDTO;
import com.rootgrouptechnologies.apiUserManager.model.DTO.PaymentDTO;
import com.rootgrouptechnologies.apiUserManager.model.DTO.ResultUserDTO;
import com.rootgrouptechnologies.apiUserManager.model.DTO.UserDTO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-11-18T01:58:59+0300",
    comments = "version: 1.5.0.Beta1, compiler: javac, environment: Java 11.0.12 (BellSoft)"
)
@Component
public class ObjectMapperImpl implements ObjectMapper {

    @Override
    public ResultUserDTO toResultDTO(User user, Licence licence, LicenceType licenceType, Billing billing, Payment payment) {
        if ( user == null && licence == null && licenceType == null && billing == null && payment == null ) {
            return null;
        }

        UserDTO userDTO = null;
        userDTO = toUserDTO( user );
        LicenceDTO licenceDTO = null;
        licenceDTO = toLicenceDTO( licence );
        LicenceTypeDTO licenceTypeDTO = null;
        licenceTypeDTO = toLicenceTypeDTO( licenceType );
        BillingDTO billingDTO = null;
        billingDTO = toBillingDTO( billing );
        PaymentDTO paymentDTO = null;
        paymentDTO = toPaymentDTO( payment );

        ResultUserDTO resultUserDTO = new ResultUserDTO( userDTO, licenceDTO, licenceTypeDTO, billingDTO, paymentDTO );

        return resultUserDTO;
    }

    @Override
    public UserDTO toUserDTO(User user) {
        if ( user == null ) {
            return null;
        }

        Integer id = null;
        String discordUsername = null;
        String discordEmail = null;
        String creationDate = null;
        String discordId = null;

        id = user.getId();
        discordUsername = user.getDiscordUsername();
        discordEmail = user.getDiscordEmail();
        creationDate = user.getCreationDate();
        if ( user.getDiscordId() != null ) {
            discordId = String.valueOf( user.getDiscordId() );
        }

        UserDTO userDTO = new UserDTO( id, discordUsername, discordEmail, creationDate, discordId );

        return userDTO;
    }

    @Override
    public PaymentDTO toPaymentDTO(Payment payment) {
        if ( payment == null ) {
            return null;
        }

        Integer amount = null;
        String comment = null;
        String paymentState = null;
        String paymentDate = null;

        amount = payment.getAmount();
        comment = payment.getComment();
        paymentState = payment.getPaymentState();
        paymentDate = payment.getPaymentDate();

        PaymentDTO paymentDTO = new PaymentDTO( amount, comment, paymentState, paymentDate );

        return paymentDTO;
    }

    @Override
    public LicenceTypeDTO toLicenceTypeDTO(LicenceType licenceType) {
        if ( licenceType == null ) {
            return null;
        }

        Integer renewalPrice = null;
        String role = null;

        renewalPrice = licenceType.getRenewPrice();
        role = licenceType.getMajorRoleName();

        LicenceTypeDTO licenceTypeDTO = new LicenceTypeDTO( renewalPrice, role );

        return licenceTypeDTO;
    }
}

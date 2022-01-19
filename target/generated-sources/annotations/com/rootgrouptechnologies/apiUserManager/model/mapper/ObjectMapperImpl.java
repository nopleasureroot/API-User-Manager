package com.rootgrouptechnologies.apiUserManager.model.mapper;

import com.rootgrouptechnologies.apiUserManager.entity.Billing;
import com.rootgrouptechnologies.apiUserManager.entity.Inventory;
import com.rootgrouptechnologies.apiUserManager.entity.Licence;
import com.rootgrouptechnologies.apiUserManager.entity.LicenceType;
import com.rootgrouptechnologies.apiUserManager.entity.Metric;
import com.rootgrouptechnologies.apiUserManager.entity.Payment;
import com.rootgrouptechnologies.apiUserManager.entity.User;
import com.rootgrouptechnologies.apiUserManager.model.DTO.BillingDTO;
import com.rootgrouptechnologies.apiUserManager.model.DTO.DropDTO;
import com.rootgrouptechnologies.apiUserManager.model.DTO.LicenceDTO;
import com.rootgrouptechnologies.apiUserManager.model.DTO.LicenceTypeDTO;
import com.rootgrouptechnologies.apiUserManager.model.DTO.MetricDTO;
import com.rootgrouptechnologies.apiUserManager.model.DTO.PaymentDTO;
import com.rootgrouptechnologies.apiUserManager.model.DTO.ResultUserDTO;
import com.rootgrouptechnologies.apiUserManager.model.DTO.UserDTO;
import java.time.LocalDate;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-01-19T21:02:58+0300",
    comments = "version: 1.5.0.Beta1, compiler: javac, environment: Java 15.0.2 (Oracle Corporation)"
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
    public DropDTO toDropDTO(Inventory inventory) {
        if ( inventory == null ) {
            return null;
        }

        String password = null;
        Integer quantity = null;
        Boolean isActive = null;
        LocalDate creationDate = null;

        password = inventory.getPassword();
        quantity = inventory.getQuantity();
        isActive = inventory.getIsActive();
        creationDate = inventory.getCreationDate();

        DropDTO dropDTO = new DropDTO( quantity, password, isActive, creationDate );

        return dropDTO;
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

    @Override
    public MetricDTO toMetricDTO(Metric metric) {
        if ( metric == null ) {
            return null;
        }

        MetricDTO metricDTO = new MetricDTO();

        metricDTO.setDate( metric.getDate() );
        if ( metric.getDepartedUsers() != null ) {
            metricDTO.setDepartedUsers( metric.getDepartedUsers() );
        }

        return metricDTO;
    }
}

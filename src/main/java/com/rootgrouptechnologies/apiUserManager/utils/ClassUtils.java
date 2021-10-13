package com.rootgrouptechnologies.apiUserManager.utils;

import com.rootgrouptechnologies.apiUserManager.db.entity.Billing;
import com.rootgrouptechnologies.apiUserManager.db.entity.Licence;
import com.rootgrouptechnologies.apiUserManager.db.entity.LicenceType;
import com.rootgrouptechnologies.apiUserManager.db.entity.User;
import kong.unirest.json.JSONObject;

public class ClassUtils {
    public static JSONObject convertData(User user, Licence licence, LicenceType licenceType, Billing billing) {
        return new JSONObject()
                .put("common-data", convertCommonData(user, licenceType))
                .put("licence-data", convertLicenceData(licence))
                .put("renew-data", convertRenewData(licence, licenceType))
                .put("billing-data", convertBillingData(billing));
    }

    private static JSONObject convertCommonData(User user, LicenceType licenceType) {
        return new JSONObject()
                .put("discordName", user.getDiscordUsername())
                .put("discordEmail", user.getDiscordEmail())
                .put("creationDate", user.getCreationDate())
                .put("discordId", user.getDiscordId())
                .put("discordAvatar", user.getDiscordAvatar())
                .put("Role", licenceType.getMajorRoleName());
    }

    private static JSONObject convertLicenceData(Licence licence) {
        return new JSONObject()
                .put("licenceKey", licence.getIdentifier())
                .put("keyBind", licence.getActivated());
    }

    private static JSONObject convertRenewData(Licence licence, LicenceType licenceType) {
        if (licence.getRenewalDate() == null)
            return  new JSONObject()
                    .put("renewDate", "Lifetime")
                    .put("renewPrice", licenceType.getaRenewPrice());

        return new JSONObject()
                .put("renewDate", licence.getRenewalDate())
                .put("renewPrice", licenceType.getaRenewPrice());
    }

    private static JSONObject convertBillingData(Billing billing) {
        if (billing.getActive() && billing.getCardDate() != null && billing.getCartNumberEnding() != null && billing.getPaymentId() != null) {
            return new JSONObject()
                    .put("cartBind", billing.getActive())
                    .put("cartEnding", billing.getCartNumberEnding())
                    .put("cartDate", billing.getCardDate())
                    .put("paymentId", billing.getPaymentId());
        }

        return new JSONObject()
                .put("cartBind", billing.getActive())
                .put("cartEnding", "cart not found")
                .put("cartDate", "cart not found")
                .put("paymentId", "cart not found");
    }
}

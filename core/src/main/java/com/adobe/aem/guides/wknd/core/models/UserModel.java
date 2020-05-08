package com.adobe.aem.guides.wknd.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

/**
 * @author kevin.
 * @Title: rhtj.
 * @Package com.adobe.aem.guides.wknd.core.models.
 * @Description: sing model.
 * @date 2020/04/3 16:22 下午.
 */
@Model(adaptables = Resource.class)
public class UserModel {

    @Inject
    private String firstName;

    @Inject
    private String lastName;

    @Inject
    private String gender;

    @Inject
    private String country;

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

}

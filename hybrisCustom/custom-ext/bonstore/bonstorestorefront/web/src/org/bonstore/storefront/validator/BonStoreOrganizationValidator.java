package org.bonstore.storefront.validator;

import org.apache.commons.lang.StringUtils;
import org.bonstore.storefront.forms.OrganizationForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Tanmoy_Mondal on 8/10/2016.
 */
@Component("bonStoreOrganizationValidator")
public class BonStoreOrganizationValidator implements Validator
{

    @Override
    public boolean supports(final Class<?> aClass)
    {
        return OrganizationForm.class.equals(aClass);
    }

    @Override
    public void validate(final Object object, final Errors errors)
    {
        final OrganizationForm organizationForm = (OrganizationForm) object;
        final String id = organizationForm.getId();
        final String phonenumber = organizationForm.getPhonenumber();
        final String email = organizationForm.getEmail();
        final String name = organizationForm.getName();
        validateOrganizationField(errors, id, "id", "account.organization.id.invalid");
        validateOrganizationField(errors, name, "name", "account.organization.name.invalid");
        validateOrganizationField(errors, phonenumber, "phonenumber", "account.organization.phonenumber.invalid");
        validateOrganizationField(errors, email, "email", "account.organization.email.invalid");

    }

    protected void validateOrganizationField(final Errors errors, final String name, final String propertyName,
                                             final String property)
    {
        if (StringUtils.isBlank(name))
        {
            errors.rejectValue(propertyName, property);
        }
    }

}

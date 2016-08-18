package org.bonstore.storefront.validator;

import de.hybris.platform.acceleratorstorefrontcommons.forms.validation.RegistrationValidator;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.bonstore.storefront.forms.BonStoreRegisterForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

/**
 * Created by Tanmoy_Mondal on 8/10/2016.
 */
@Component("bonStoreRegistrationValidator")
public class BonStoreRegistrationValidator extends RegistrationValidator
{

    @Override
    public void validate(final Object object, final Errors errors)
    {
        final BonStoreRegisterForm bonStoreRegisterForm = (BonStoreRegisterForm) object;
        final String titleCode = bonStoreRegisterForm.getTitleCode();
        final String firstName = bonStoreRegisterForm.getFirstName();
        final String lastName = bonStoreRegisterForm.getLastName();
        final String email = bonStoreRegisterForm.getEmail();
        final String pwd = bonStoreRegisterForm.getPwd();
        final String checkPwd = bonStoreRegisterForm.getCheckPwd();
        final List<String> organizationIds = bonStoreRegisterForm.getOrganizationIds();

        validateTitleCode(errors, titleCode);
        validateName(errors, firstName, "firstName", "register.firstName.invalid");
        validateName(errors, lastName, "lastName", "register.lastName.invalid");

        if (StringUtils.length(firstName) + StringUtils.length(lastName) > 255)
        {
            errors.rejectValue("lastName", "register.name.invalid");
            errors.rejectValue("firstName", "register.name.invalid");
        }

        validateEmail(errors, email);
        validatePassword(errors, pwd);
        comparePasswords(errors, pwd, checkPwd);
        validateOrganization(errors, organizationIds, "organizationIds", "register.organization.empty");
    }

    protected void validateOrganization(final Errors errors, final List<String> orgList, final String propertyName,
                                        final String property)
    {
        if (null == orgList || orgList.isEmpty())
        {
            errors.rejectValue(propertyName, property);
        }
    }

}
